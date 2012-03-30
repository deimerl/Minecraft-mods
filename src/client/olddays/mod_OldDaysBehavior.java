package net.minecraft.src;
import java.util.*;
import java.io.*;
import net.minecraft.client.Minecraft;

public class mod_OldDaysBehavior extends mod_OldDays{
    public void load(){
        addProperty(this, 1, "Old mob AI",             false, "OldAI");
        addProperty(this, 2, "Animal panic",           true,  "AnimalsFlee");
        addProperty(this, 3, "Sheep eat grass",        true,  "SheepEatGrass");
        addProperty(this, 4, "Survival Test creepers", false, "SurvivalTestCreepers");
        addProperty(this, 5, "Survival Test zombies",  false, "SurvivalTestZombies");
        loadModuleProperties(4);
    }

    public void callback (int i){
        switch(i){
            case 1: EntityLiving.newai =           !OldAI;
                    EntityCreeper.fixai =           OldAI;
                    EntitySkeleton.fixai =          OldAI;
                    EntitySnowman.fixai =           OldAI;                break;
            case 2: EntityAIPanic.disablePanic =   !AnimalsFlee;
                    EntityCreature.nopanic =       !AnimalsFlee;          break;
            case 3: EntityAIEatGrass2.caneatgrass = SheepEatGrass;        break;
            case 4: EntityCreeper.survivaltest =    SurvivalTestCreepers; break;
            case 5: EntityCreature.fastzombies =    SurvivalTestZombies;
                    EntityZombie.burns =           !SurvivalTestZombies;  break;
        }
    }

    public static boolean OldAI;
    public static boolean AnimalsFlee = true;
    public static boolean SheepEatGrass = true;
    public static boolean SurvivalTestCreepers;
    public static boolean SurvivalTestZombies;
}