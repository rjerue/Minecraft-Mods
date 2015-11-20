package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class CustomArmor extends ItemArmor{

	public final String name;
	
	public CustomArmor(ArmorMaterial material, int renderIndex, int armorType, String name) {
		super(material, renderIndex, armorType);
        // TODO Auto-generated constructor stub
        this.name = name;
        this.setUnlocalizedName(ExampleMod.MODID + "_" + name);
        this.setCreativeTab(CreativeTabs.tabCombat);
         
         
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		if(slot == 2){
			return "examplemod:textures/models/armor/custom_armor_layer_2.png";
		}
		return "examplemod:textures/models/armor/custom_armor_layer_1.png";
		
		
	}
}