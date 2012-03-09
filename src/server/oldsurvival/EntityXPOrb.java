package net.minecraft.src;

import java.util.Random;

public class EntityXPOrb extends Entity
{
    public int xpColor;

    /** The age of the XP orb in ticks. */
    public int xpOrbAge;
    public int field_35158_c;

    /** The health of this XP orb. */
    private int xpOrbHealth;

    /** This is how much XP this orb has. */
    private int xpValue;

    public EntityXPOrb(World par1World, double par2, double par4, double par6, int par8)
    {
        super(par1World);
        xpOrbAge = 0;
        xpOrbHealth = 5;
        setSize(0.5F, 0.5F);
        yOffset = height / 2.0F;
        setPosition(par2, par4, par6);
        rotationYaw = (float)(Math.random() * 360D);
        motionX = (float)(Math.random() * 0.2D - 0.1D) * 2.0F;
        motionY = (float)(Math.random() * 0.2D) * 2.0F;
        motionZ = (float)(Math.random() * 0.2D - 0.1D) * 2.0F;
        xpValue = par8;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public EntityXPOrb(World par1World)
    {
        super(par1World);
        xpOrbAge = 0;
        xpOrbHealth = 5;
        setSize(0.25F, 0.25F);
        yOffset = height / 2.0F;
    }

    protected void entityInit()
    {
        if (mod_OldSurvivalMode.DisableXP){
            this.setEntityDead();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (field_35158_c > 0)
        {
            field_35158_c--;
        }

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= 0.03D;

        if (worldObj.getBlockMaterial(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) == Material.lava)
        {
            motionY = 0.2D;
            motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
            motionZ = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
            worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 2.0F + rand.nextFloat() * 0.4F);
        }

        pushOutOfBlocks(posX, (boundingBox.minY + boundingBox.maxY) / 2D, posZ);
        double d = 8D;
        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, d);

        if (entityplayer != null)
        {
            double d1 = (entityplayer.posX - posX) / d;
            double d2 = ((entityplayer.posY + (double)entityplayer.getEyeHeight()) - posY) / d;
            double d3 = (entityplayer.posZ - posZ) / d;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D)
            {
                d5 *= d5;
                motionX += (d1 / d4) * d5 * 0.1D;
                motionY += (d2 / d4) * d5 * 0.1D;
                motionZ += (d3 / d4) * d5 * 0.1D;
            }
        }

        moveEntity(motionX, motionY, motionZ);
        float f = 0.98F;

        if (onGround)
        {
            f = 0.5880001F;
            int i = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));

            if (i > 0)
            {
                f = Block.blocksList[i].slipperiness * 0.98F;
            }
        }

        motionX *= f;
        motionY *= 0.98D;
        motionZ *= f;

        if (onGround)
        {
            motionY *= -0.9D;
        }

        xpColor++;
        xpOrbAge++;

        if (xpOrbAge >= 6000)
        {
            setEntityDead();
        }
    }

    /**
     * Returns if this entity is in water and will end up adding the waters velocity to the entity
     */
    public boolean handleWaterMovement()
    {
        return worldObj.handleMaterialAcceleration(boundingBox, Material.water, this);
    }

    /**
     * Will deal the specified amount of damage to the entity if the entity isn't immune to fire damage. Args:
     * amountDamage
     */
    protected void dealFireDamage(int par1)
    {
        attackEntityFrom(DamageSource.inFire, par1);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        setBeenAttacked();
        xpOrbHealth -= par2;

        if (xpOrbHealth <= 0)
        {
            setEntityDead();
        }

        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("Health", (byte)xpOrbHealth);
        par1NBTTagCompound.setShort("Age", (short)xpOrbAge);
        par1NBTTagCompound.setShort("Value", (short)xpValue);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        xpOrbHealth = par1NBTTagCompound.getShort("Health") & 0xff;
        xpOrbAge = par1NBTTagCompound.getShort("Age");
        xpValue = par1NBTTagCompound.getShort("Value");
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (worldObj.isRemote)
        {
            return;
        }

        if (field_35158_c == 0 && par1EntityPlayer.xpCooldown == 0)
        {
            par1EntityPlayer.xpCooldown = 2;
            worldObj.playSoundAtEntity(this, "random.orb", 0.1F, 0.5F * ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.8F));
            par1EntityPlayer.onItemPickup(this, 1);
            par1EntityPlayer.addExperience(xpValue);
            setEntityDead();
        }
    }

    /**
     * Returns the XP value of this XP orb.
     */
    public int getXpValue()
    {
        return xpValue;
    }

    /**
     * Get a fragment of the maximum experience points value for the supplied value of experience points value.
     */
    public static int getXPSplit(int par0)
    {
        if (par0 >= 2477)
        {
            return 2477;
        }

        if (par0 >= 1237)
        {
            return 1237;
        }

        if (par0 >= 617)
        {
            return 617;
        }

        if (par0 >= 307)
        {
            return 307;
        }

        if (par0 >= 149)
        {
            return 149;
        }

        if (par0 >= 73)
        {
            return 73;
        }

        if (par0 >= 37)
        {
            return 37;
        }

        if (par0 >= 17)
        {
            return 17;
        }

        if (par0 >= 7)
        {
            return 7;
        }

        return par0 < 3 ? 1 : 3;
    }

    public boolean func_48313_k_()
    {
        return false;
    }
}