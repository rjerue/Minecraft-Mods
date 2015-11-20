package com.camp.block;

import net.minecraftforge.fml.common.registry.GameRegistry;
	
public class BlockManager {
	public static CustomBlock customBlock;
	public static void mainRegistry(){
		intializeBlock();
		registerBlock();
	}
	public static void intializeBlock(){
		customBlock = new CustomBlock();
	}
	public static void registerBlock(){
		GameRegistry.registerBlock(customBlock, customBlock.name);
		
	}
}
