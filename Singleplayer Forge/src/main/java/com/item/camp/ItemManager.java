package com.item.camp;

import com.example.examplemod.ExampleMod;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemManager {
	public static CustomItem customItem;
	public static CustomSword customSword;
	public static CustomArmor customHelmet;
	public static CustomArmor customChestplate;
	public static CustomArmor customLeggings;
	public static CustomArmor customBoots;
	//
	public static CustomHoe customHoe;
	public static EmeraldAxe customAxe;
	public static EmeraldPickaxe customPickaxe;
	public static EmeraldShovel customShovel;
	public static void mainRegistry(){
		intializeItem();
		registerItem();
	}
	public static void intializeItem(){
		//customItem = new CustomItem();
		customSword = new CustomSword(ExampleMod.customToolMaterial);
		customHelmet = new CustomArmor(ExampleMod.customArmorMaterial, 0, 0, "CustomHelmet");
		customChestplate = new CustomArmor(ExampleMod.customArmorMaterial, 0, 1, "CustomChestplate");
		customLeggings = new CustomArmor(ExampleMod.customArmorMaterial, 0, 2, "CustomLeggings");
		customBoots = new CustomArmor(ExampleMod.customArmorMaterial, 0, 3, "CustomBoots");
		customHoe = new CustomHoe(ExampleMod.customToolMaterial);
		//
		customAxe = new EmeraldAxe(ExampleMod.customToolMaterial);
		customPickaxe = new EmeraldPickaxe(ExampleMod.customToolMaterial);
		customShovel = new EmeraldShovel(ExampleMod.customToolMaterial);
	}			
	public static void registerItem(){
		//GameRegistry.registerItem(customItem, customItem.name);
		GameRegistry.registerItem(customSword, customSword.name);
		GameRegistry.registerItem(customHelmet, customHelmet.name);
		GameRegistry.registerItem(customChestplate, customChestplate.name);
		GameRegistry.registerItem(customLeggings, customLeggings.name);
		GameRegistry.registerItem(customBoots, customBoots.name);
		GameRegistry.registerItem(customHoe, customHoe.name);
		//
		GameRegistry.registerItem(customAxe, customAxe.name);
		GameRegistry.registerItem(customShovel, customShovel.name);
		GameRegistry.registerItem(customPickaxe, customPickaxe.name);
	}
}