package net.minecraft.src.nbxlite.mapgens;

import net.minecraft.src.*;

public class GenLayerBiome13 extends GenLayer
{
    private BiomeGenBase allowedBiomes[];

    public GenLayerBiome13(long par1, GenLayer par3GenLayer, WorldType par4WorldType)
    {
        super(par1);
        allowedBiomes = (new BiomeGenBase[]
                {
                    BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.jungle
                });
        parent = par3GenLayer;

        if (par4WorldType == WorldType.DEFAULT_1_1)
        {
            allowedBiomes = (new BiomeGenBase[]
                    {
                        BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga
                    });
        }
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int ai[] = parent.getInts(par1, par2, par3, par4);
        int ai1[] = IntCache.getIntCache(par3 * par4);

        for (int i = 0; i < par4; i++)
        {
            for (int j = 0; j < par3; j++)
            {
                initChunkSeed(j + par1, i + par2);
                int k = ai[j + i * par3];

                if (k == 0)
                {
                    ai1[j + i * par3] = 0;
                    continue;
                }

                if (k == BiomeGenBase.mushroomIsland.biomeID)
                {
                    ai1[j + i * par3] = k;
                    continue;
                }

                if (k == 1)
                {
                    ai1[j + i * par3] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                }
                else
                {
                    ai1[j + i * par3] = BiomeGenBase.icePlains.biomeID;
                }
            }
        }

        return ai1;
    }
}
