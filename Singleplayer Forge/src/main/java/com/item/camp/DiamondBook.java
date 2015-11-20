package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;

public class DiamondBook extends ItemBook{

	public final String name = "DiamondBook";
	public DiamondBook (){
		this.setUnlocalizedName(ExampleMod.MODID + "_" + this.name);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	@Override
	public int getItemEnchantability()
    {
        return 25;
    }
}
