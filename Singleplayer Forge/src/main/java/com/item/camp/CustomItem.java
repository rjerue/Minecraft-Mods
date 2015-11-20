package com.item.camp;
import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class CustomItem extends Item {
	public static final String name = "CustomItem";
	
	public CustomItem(){
		super();
		this.setUnlocalizedName(ExampleMod.MODID +  "_" + this.name);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos position, EnumFacing side, float hitX, float hitY, float hitZ){
		world.createExplosion(player, position.getX() + hitX, position.getY() + hitY, position.getZ() + hitZ, 20f, true);
		return super.onItemUse(stack, player, world, position, side, hitX, hitY, hitZ);
	}
	
} 