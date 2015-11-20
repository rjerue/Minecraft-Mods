package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class CustomSword extends ItemSword {
	public final String name = "CustomSword";
	protected CustomSword(ToolMaterial material){
		super(material);
		this.setUnlocalizedName(ExampleMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabCombat);
		
	}
}
