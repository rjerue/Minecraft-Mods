package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;

public class EmeraldAxe extends ItemAxe{
	public final String name = "EmeraldAxe";
	
	protected EmeraldAxe (ToolMaterial material){
		super(material);
		this.setUnlocalizedName(ExampleMod.MODID + "_" + this.name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxDamage(2343);
		this.canRepair(true);
		//this.toolMaterial(ExampleMod.emeraldToolMaterial);
	}

	private void canRepair(boolean b) {
		// TODO Auto-generated method stub
		
	}
}