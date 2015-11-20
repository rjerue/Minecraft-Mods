package com.item.camp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;

import com.example.examplemod.ExampleMod;

public class EmeraldShovel extends ItemSpade{
		public final String name = "EmeraldShovel";
		
		protected EmeraldShovel (ToolMaterial material){
			super(material);
			this.setUnlocalizedName(ExampleMod.MODID + "_" + this.name);
			this.setCreativeTab(CreativeTabs.tabTools);
		}
}