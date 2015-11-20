package com.camp.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

public class CustomBiome extends BiomeGenBase {
	
	public CustomBiome(int p_i1971_1_, boolean register) {
        super(p_i1971_1_, register);
        // TODO Auto-generated constructor stub
        this.fillerBlock = Blocks.air.getDefaultState();
        this.topBlock = Blocks.air.getStateFromMeta(1);
        this.waterColorMultiplier = 0x11FF11;
         
	}
}
