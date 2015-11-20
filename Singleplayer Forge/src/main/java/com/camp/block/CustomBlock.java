package com.camp.block;

import com.example.examplemod.ExampleMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CustomBlock extends Block{
	public static final String name = "CustomBlock";
	public CustomBlock(){
		super(Material.rock);
		this.setUnlocalizedName(ExampleMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setLightLevel(1);
		this.setHardness(10);
		this.setResistance(7000);
		this.setHarvestLevel("pickaxe", 3);		
	}
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn){
		super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
		if(entityIn instanceof EntityLivingBase);
			entityIn.setFire(100);
		
		
	}
}