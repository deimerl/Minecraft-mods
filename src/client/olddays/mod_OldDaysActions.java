package net.minecraft.src;

public class mod_OldDaysActions extends mod_OldDays{
    public void load(){
        registerModule(0);
        addProperty(this, 1, "Punch TNT",           true,  "PunchTNT");
        addProperty(this, 2, "Extinguish TNT",      false, "ExtinguishTNT");
        addProperty(this, 3, "Smelt items on fire", false, "SmeltOnFire");
        addProperty(this, 4, "Unnerfed fire",       true,  "OldFire");
        addProperty(this, 5, "Punch sheep",         true,  "PunchSheep");
        addProperty(this, 6, "Old tool durability", false, "OldTools");
        addProperty(this, 7, "Mushroom spreading",  true,  "ShroomSpreading");
        addProperty(this, 8, "Solid TNT",           true,  "SolidTNT");
        loadModuleProperties();
        replaceBlocks();
    }

    public void callback (int i){
        switch(i){
            case 1: setBool("BlockTNT2", "punchToActivate", PunchTNT); break;
            case 2: setBool("EntityTNTPrimed", "extinguish", ExtinguishTNT); break;
            case 3: setBool("EntityItem", "smeltOnFire", SmeltOnFire); break;
            case 4: setBool("BlockFire", "oldFire", OldFire);
                    setBool("BlockFire", "infiniteBurn", OldFire); break;
            case 5: setBool("EntitySheep", "punchToShear", PunchSheep); break;
            case 6: setToolDurability(OldTools); break;
            case 7: setBool("BlockMushroom", "spreading", ShroomSpreading); break;
            case 8: setSolidTNT(SolidTNT); break;
        }
    }

    public static boolean SmeltOnFire;
    public static boolean PunchTNT = true;
    public static boolean ExtinguishTNT;
    public static boolean OldFire= true;
    public static boolean PunchSheep = true;
    public static boolean OldTools;
    public static boolean ShroomSpreading = true;
    public static boolean SolidTNT = true;
//Old blocks
//Unflammable fences and stairs

    private void setSolidTNT(boolean b){
        try{
            ModLoader.setPrivateValue(net.minecraft.src.Material.class, Material.tnt, 33, !b);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void replaceBlocks(){
        try{
            Block.blocksList[Block.tnt.blockID] = null;
            BlockTNT2 customtnt = (BlockTNT2)(new BlockTNT2(46, 8));
            customtnt.setHardness(0.0F);
            customtnt.setStepSound(Block.soundGrassFootstep);
            customtnt.setBlockName("tnt");
            Block.blocksList[Block.tnt.blockID] = customtnt;
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    private void setToolDurability(boolean b){
        try{
            int wood = 59;
            int stone = 131;
            int iron = 250;
            int gold = 32;
            int diamond = 1561;
            if (b){
                wood = 31;
                stone = 63;
                iron = 127;
                gold = 15;
                diamond = 255;
            }
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.pickaxeWood, 145, wood);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.axeWood, 145, wood);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.shovelWood, 145, wood);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.swordWood, 145, wood);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.hoeWood, 145, wood);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.pickaxeStone, 145, stone);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.axeStone, 145, stone);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.shovelStone, 145, stone);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.swordStone, 145, stone);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.hoeStone, 145, stone);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.pickaxeSteel, 145, iron);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.axeSteel, 145, iron);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.shovelSteel, 145, iron);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.swordSteel, 145, iron);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.hoeSteel, 145, iron);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.pickaxeGold, 145, gold);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.axeGold, 145, gold);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.shovelGold, 145, gold);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.swordGold, 145, gold);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.hoeGold, 145, gold);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.pickaxeDiamond, 145, diamond);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.axeDiamond, 145, diamond);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.shovelDiamond, 145, diamond);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.swordDiamond, 145, diamond);
            ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.hoeDiamond, 145, diamond);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}