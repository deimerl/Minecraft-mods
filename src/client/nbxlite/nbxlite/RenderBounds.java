package net.minecraft.src.nbxlite;

import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class RenderBounds{
    private static RenderEngine renderEngine;
    private static Minecraft mc;
    private static World worldObj;

    private static void renderGroundBounds(float f, float ff){
        Tessellator tessellator = Tessellator.instance;
        float f1 = ODNBXlite.SurrGroundHeight - 1;
        int i1 = 128;
        if (i1 > ODNBXlite.IndevWidthX){
            i1 = ODNBXlite.IndevWidthX;
        }
        if (i1 > ODNBXlite.IndevWidthZ){
            i1 = ODNBXlite.IndevWidthZ;
        }
        Block block = Block.blocksList[ODNBXlite.SurrGroundType];
        if (block==Block.grass && (ODNBXlite.SurrGroundHeight <= ODNBXlite.SurrWaterHeight || ODNBXlite.SurrWaterType==Block.lavaStill.blockID)){
            block = Block.dirt;
        }
        if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_CLASSIC){
            block = Block.bedrock;
        }
        f1 += (float)block.getBlockBoundsMaxY();
        double dd = 0D;
        double dd1 = (double)i1;
        double dd2 = 0D;
        double dd3 = (double)i1;
        int i = block.colorMultiplier(worldObj, 0, ODNBXlite.SurrWaterHeight, 0);
        float ff1 = (float)(i >> 16 & 0xff) / 255F;
        float ff2 = (float)(i >> 8 & 0xff) / 255F;
        float ff3 = (float)(i & 0xff) / 255F;
        if (EntityRenderer.anaglyphEnable)
        {
            float ff4 = (ff1 * 30F + ff2 * 59F + ff3 * 11F) / 100F;
            float ff5 = (ff1 * 30F + ff2 * 70F) / 100F;
            float ff6 = (ff1 * 30F + ff3 * 70F) / 100F;
            ff1 = ff4;
            ff2 = ff5;
            ff3 = ff6;
        }
        int i2 = 2048 / i1;
        tessellator.startDrawingQuads();
        double d = mc.renderViewEntity.lastTickPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.lastTickPosX) * (double)f;
        double d1 = mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * (double)f;
        double d2 = mc.renderViewEntity.lastTickPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.lastTickPosZ) * (double)f;
        tessellator.setTranslation(-d, -d1, -d2);
        GL11.glColor3f(ff * ff1, ff * ff2, ff * ff3);
        for (int i3 = -i1 * i2; i3 < ODNBXlite.IndevWidthX + i1 * i2; i3 += i1){
            for (int i5 = -i1 * i2; i5 < ODNBXlite.IndevWidthZ + i1 * i2; i5 += i1){
                if ((f1 < 0.0F) || (i3 < 0) || (i5 < 0) || (i3 >= ODNBXlite.IndevWidthX) || (i5 >= ODNBXlite.IndevWidthZ)){
                    tessellator.addVertexWithUV(i3, f1, i5 + i1, dd1, dd3);
                    tessellator.addVertexWithUV(i3 + i1, f1, i5 + i1, dd1, dd2);
                    tessellator.addVertexWithUV(i3 + i1, f1, i5, dd, dd2);
                    tessellator.addVertexWithUV(i3, f1, i5, dd, dd3);
                }
            }
        }
        tessellator.draw();
    }

    private static void renderSideBounds(float f){
        Tessellator tessellator = Tessellator.instance;
        Block block = Block.blocksList[ODNBXlite.SurrGroundType];
        if (block==Block.grass && (ODNBXlite.SurrGroundHeight <= ODNBXlite.SurrWaterHeight || ODNBXlite.SurrWaterType==Block.lavaStill.blockID)){
            block = Block.dirt;
        }
        if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_CLASSIC){
            block = Block.bedrock;
        }
        float f1 = (float)block.getBlockBoundsMaxY();
        int i1 = 128;
        if (i1 > ODNBXlite.IndevWidthX){
            i1 = ODNBXlite.IndevWidthX;
        }
        if (i1 > ODNBXlite.IndevWidthZ){
            i1 = ODNBXlite.IndevWidthZ;
        }
        double dd = (double)i1;
        double dd1 = 0D;
        double dd2 = 0D;
        double dd3 = (double)i1;
        tessellator.startDrawingQuads();
        double d = mc.renderViewEntity.lastTickPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.lastTickPosX) * (double)f;
        double d1 = mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * (double)f;
        double d2 = mc.renderViewEntity.lastTickPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.lastTickPosZ) * (double)f;
        float i = ODNBXlite.SurrGroundHeight - i1 + f1 - 1;
        tessellator.setTranslation(-d, -d1, -d2);
        for (int i3 = 0; i3 < ODNBXlite.IndevWidthZ; i3 += i1){
            tessellator.addVertexWithUV(0, ODNBXlite.SurrGroundHeight, i3, dd1, dd2);
            tessellator.addVertexWithUV(0, ODNBXlite.SurrGroundHeight, i3 + i1, dd1, dd3);
            tessellator.addVertexWithUV(0, i, i3 + i1, dd, dd3);
            tessellator.addVertexWithUV(0, i, i3, dd, dd2);
        }
        for (int i3 = 0; i3 < ODNBXlite.IndevWidthX; i3 += i1){
            tessellator.addVertexWithUV(i3, ODNBXlite.SurrGroundHeight, ODNBXlite.IndevWidthZ, dd1, dd2);
            tessellator.addVertexWithUV(i3 + i1, ODNBXlite.SurrGroundHeight, ODNBXlite.IndevWidthZ, dd1, dd3);
            tessellator.addVertexWithUV(i3 + i1, i, ODNBXlite.IndevWidthZ, dd, dd3);
            tessellator.addVertexWithUV(i3, i, ODNBXlite.IndevWidthZ, dd, dd2);
        }
        dd2 = (double)i1;
        dd3 = 0D;
        for (int i3 = 0; i3 < ODNBXlite.IndevWidthZ; i3 += i1){
            tessellator.addVertexWithUV(ODNBXlite.IndevWidthX, i, i3, dd, dd2);
            tessellator.addVertexWithUV(ODNBXlite.IndevWidthX, i, i3 + i1, dd, dd3);
            tessellator.addVertexWithUV(ODNBXlite.IndevWidthX, ODNBXlite.SurrGroundHeight, i3 + i1, dd1, dd3);
            tessellator.addVertexWithUV(ODNBXlite.IndevWidthX, ODNBXlite.SurrGroundHeight, i3, dd1, dd2);
        }
        for (int i3 = 0; i3 < ODNBXlite.IndevWidthX; i3 += i1){
            tessellator.addVertexWithUV(i3, i, 0, dd, dd2);
            tessellator.addVertexWithUV(i3 + i1, i, 0, dd, dd3);
            tessellator.addVertexWithUV(i3 + i1, ODNBXlite.SurrGroundHeight, 0, dd1, dd3);
            tessellator.addVertexWithUV(i3, ODNBXlite.SurrGroundHeight, 0, dd1, dd2);
        }
        tessellator.draw();
    }

    private static void renderBottomBounds(float f){
        Tessellator tessellator = Tessellator.instance;
        float f1 = 0;
        int i1 = 128;
        if (i1 > ODNBXlite.IndevWidthX){
            i1 = ODNBXlite.IndevWidthX;
        }
        if (i1 > ODNBXlite.IndevWidthZ){
            i1 = ODNBXlite.IndevWidthZ;
        }
        double dd = 0D;
        double dd1 = (double)i1;
        double dd2 = 0D;
        double dd3 = (double)i1;
        tessellator.startDrawingQuads();
        double d = mc.renderViewEntity.lastTickPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.lastTickPosX) * (double)f;
        double d1 = mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * (double)f;
        double d2 = mc.renderViewEntity.lastTickPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.lastTickPosZ) * (double)f;
        tessellator.setTranslation(-d, -d1, -d2);
        for (int i3 = 0; i3 < ODNBXlite.IndevWidthX; i3 += i1){
            for (int i5 = 0; i5 < ODNBXlite.IndevWidthZ; i5 += i1){
                tessellator.addVertexWithUV(i3, f1, i5 + i1, dd1, dd3);
                tessellator.addVertexWithUV(i3 + i1, f1, i5 + i1, dd1, dd2);
                tessellator.addVertexWithUV(i3 + i1, f1, i5, dd, dd2);
                tessellator.addVertexWithUV(i3, f1, i5, dd, dd3);
            }
        }
        tessellator.draw();
    }

    public static void renderBounds(Minecraft m, float f)
    {
        if (ODNBXlite.SurrGroundType<=0 || ODNBXlite.SurrWaterType<=0){
            return;
        }
        mc = m;
        renderEngine = mc.renderEngine;
        worldObj = mc.theWorld;
        int id = ODNBXlite.SurrGroundType;
        if (ODNBXlite.SurrGroundHeight<=ODNBXlite.SurrWaterHeight || ODNBXlite.SurrWaterType==Block.lavaStill.blockID && ODNBXlite.SurrGroundType==Block.grass.blockID){
            id = Block.dirt.blockID;
        }
        if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_CLASSIC){
            id = Block.bedrock.blockID;
        }
        GL11.glPushMatrix();
        float ff = 1.0F;
        if (!Minecraft.oldlighting){
            mc.entityRenderer.enableLightmap(f);
            int l = ODNBXlite.getLightInBounds(ODNBXlite.SurrGroundHeight);
            int i1 = l % 0x10000;
            int j1 = l / 0x10000;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i1 / 1.0F, (float)j1 / 1.0F);
        }else{
            ff = ODNBXlite.getLightFloat(ODNBXlite.SurrGroundHeight);
        }
        boolean anim = ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INDEV;
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glRotatef(90F, 0F, 0F, 1F);
        renderEngine.bindTexture("/textures/blocks/" + Block.blocksList[id].getBlockTextureFromSide(1).getIconName() + ".png");
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        renderGroundBounds(f, ff);
        if (ODNBXlite.MapFeatures == ODNBXlite.FEATURES_CLASSIC && (ODNBXlite.SurrGroundHeight>=0 || ODNBXlite.SurrWaterHeight>=0)){
            renderEngine.bindTexture("/textures/blocks/" + Block.bedrock.getBlockTextureFromSide(2).getIconName() + ".png");
            renderSideBounds(f);
            renderEngine.bindTexture("/textures/blocks/" + Block.bedrock.getBlockTextureFromSide(1).getIconName() + ".png");
            renderBottomBounds(f);
        }
        if (!Minecraft.oldlighting){
            int l = ODNBXlite.getLightInBounds(ODNBXlite.SurrWaterHeight);
            int i1 = l % 0x10000;
            int j1 = l / 0x10000;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i1 / 1.0F, (float)j1 / 1.0F);
        }else{
            ff = ODNBXlite.getLightFloat(ODNBXlite.SurrWaterHeight);
        }
        if (anim){
            renderEngine.bindTexture("/textures/blocks/" + Block.blocksList[ODNBXlite.SurrWaterType].getBlockTextureFromSide(1).getIconName() + ".png");
        }else{
            String name = Block.blocksList[ODNBXlite.SurrWaterType].getUnlocalizedName().replace("tile.", "").replace("Still", "").replace("Moving", "");
            renderEngine.bindTexture("/olddays/classic_" + name + ".png");
        }
        GL11.glEnable(GL11.GL_BLEND);
        renderLiquidBounds(f, ff);
        GL11.glRotatef(-90F, 0F, 0F, 1F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_BLEND);
        if (!Minecraft.oldlighting){
            mc.entityRenderer.disableLightmap(f);
        }
        GL11.glPopMatrix();
    }

    private static void renderLiquidBounds(float f, float ff)
    {
        float f1 = (float)ODNBXlite.SurrWaterHeight;
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.instance;
        int i1 = 128;
        if (i1 > ODNBXlite.IndevWidthX){
            i1 = ODNBXlite.IndevWidthX;
        }
        if (i1 > ODNBXlite.IndevWidthZ){
            i1 = ODNBXlite.IndevWidthZ;
        }
        double dd = 0D;
        double dd1 = (double)i1;
        double dd2 = 0D;
        double dd3 = (double)i1;
        int i = Block.blocksList[ODNBXlite.SurrWaterType].colorMultiplier(worldObj, 0, ODNBXlite.SurrWaterHeight, 0);
        float ff1 = (float)(i >> 16 & 0xff) / 255F;
        float ff2 = (float)(i >> 8 & 0xff) / 255F;
        float ff3 = (float)(i & 0xff) / 255F;
        if (EntityRenderer.anaglyphEnable)
        {
            float ff4 = (ff1 * 30F + ff2 * 59F + ff3 * 11F) / 100F;
            float ff5 = (ff1 * 30F + ff2 * 70F) / 100F;
            float ff6 = (ff1 * 30F + ff3 * 70F) / 100F;
            ff1 = ff4;
            ff2 = ff5;
            ff3 = ff6;
        }
        GL11.glColor3f(ff * ff1, ff * ff2, ff * ff3);
        int i2 = 2048 / i1;
        tessellator.startDrawingQuads();
        float f2 = (float)Block.waterStill.getBlockBoundsMinX();
        float f3 = (float)Block.waterStill.getBlockBoundsMinZ();
        double d = mc.renderViewEntity.lastTickPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.lastTickPosX) * (double)f;
        double d1 = mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * (double)f;
        double d2 = mc.renderViewEntity.lastTickPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.lastTickPosZ) * (double)f;
        tessellator.setTranslation(-d, -d1, -d2);
        for (int i3 = -i1 * i2; i3 < ODNBXlite.IndevWidthX + i1 * i2; i3 += i1){
            for (int i5 = -i1 * i2; i5 < ODNBXlite.IndevWidthZ + i1 * i2; i5 += i1){
                float f4 = f1 + (float)Block.waterStill.getBlockBoundsMaxY() - 1.1121F;
                if ((f1 < 0.0F) || (i3 < 0) || (i5 < 0) || (i3 >= ODNBXlite.IndevWidthX) || (i5 >= ODNBXlite.IndevWidthZ)){
                    tessellator.addVertexWithUV(i3 + f2, f4, i5 + i1 + f3, dd1, dd3);
                    tessellator.addVertexWithUV(i3 + i1 + f2, f4, i5 + i1 + f3, dd1, dd2);
                    tessellator.addVertexWithUV(i3 + i1 + f2, f4, i5 + f3, dd, dd2);
                    tessellator.addVertexWithUV(i3 + f2, f4, i5 + f3, dd, dd3);
                    if (ODNBXlite.SurrWaterType==Block.waterStill.blockID || ODNBXlite.SurrWaterType==Block.waterMoving.blockID){
                        tessellator.addVertexWithUV(i3 + f2, f4, i5 + f3, dd, dd3);
                        tessellator.addVertexWithUV(i3 + i1 + f2, f4, i5 + f3, dd, dd2);
                        tessellator.addVertexWithUV(i3 + i1 + f2, f4, i5 + i1 + f3, dd1, dd2);
                        tessellator.addVertexWithUV(i3 + f2, f4, i5 + i1 + f3, dd1, dd3);
                    }
                }
            }
        }
        tessellator.draw();
        tessellator.setTranslation(0, 0, 0);
//         GL11.glDisable(GL11.GL_BLEND);
    }
}