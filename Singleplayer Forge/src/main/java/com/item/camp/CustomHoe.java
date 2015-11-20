package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;

public class CustomHoe extends ItemHoe{
	public final String name = "CustomHoe";
	protected CustomHoe(ToolMaterial material){
		super(material);
		this.setUnlocalizedName(ExampleMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabTools);

	}
}
