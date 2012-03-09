package net.minecraft.src;

public class EntityAIPanic extends EntityAIBase
{
    private EntityCreature field_48316_a;
    private float field_48314_b;
    private double field_48315_c;
    private double field_48312_d;
    private double field_48313_e;

    public EntityAIPanic(EntityCreature par1EntityCreature, float par2)
    {
        field_48316_a = par1EntityCreature;
        field_48314_b = par2;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!mod_OldSurvivalMode.AnimalsFlee){
            return false;
        }
        if (field_48316_a.getAITarget() == null)
        {
            return false;
        }

        Vec3D vec3d = RandomPositionGenerator.func_48622_a(field_48316_a, 5, 4);

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            field_48315_c = vec3d.xCoord;
            field_48312_d = vec3d.yCoord;
            field_48313_e = vec3d.zCoord;
            return true;
        }
    }

    public void func_46080_e()
    {
        field_48316_a.func_48084_aL().func_48666_a(field_48315_c, field_48312_d, field_48313_e, field_48314_b);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48316_a.func_48084_aL().func_46072_b();
    }
}