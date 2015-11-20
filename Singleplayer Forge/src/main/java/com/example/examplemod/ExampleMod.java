package com.example.examplemod;

import com.camp.block.BlockManager;
import com.camp.entity.CustomMob;
import com.camp.entity.RenderCustomBiped;
import com.item.camp.ItemManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static ToolMaterial customToolMaterial;
    public static ArmorMaterial customArmorMaterial;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	customToolMaterial = EnumHelper.addToolMaterial("Custom", 3, 2000, 10, 6.0f, 20);
    	customToolMaterial = EnumHelper.addToolMaterial("Custom", 3, 2343, 10000, 0.0f, 20);
    	customArmorMaterial = EnumHelper.addArmorMaterial("CustomHelmet", "customHelmet", 40, new int[] {4, 9, 7, 4}, 17);
    	customArmorMaterial = EnumHelper.addArmorMaterial("CustomChestplate", "customChestplate", 90, new int[] {4, 9, 7, 4}, 17);
    	customArmorMaterial = EnumHelper.addArmorMaterial("CustomLeggings", "customLeggings", 70, new int[] {4, 9, 7, 4}, 17);
    	customArmorMaterial = EnumHelper.addArmorMaterial("CustomBoots", "cutomBoots", 40, new int[] {4, 9, 7, 4}, 17);
    	ItemManager.mainRegistry();
    	BlockManager.mainRegistry();
    	createEntity(CustomMob.class, "CustomMob", 0x81f910, 0x54a705);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customSword), "x","x","y", 'x', Items.emerald, 'y', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customHelmet), "xxx","x x", 'x', Items.emerald);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customChestplate), "x x","xxx","xxx", 'x', Items.emerald);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customLeggings), "xxx","x x","x x", 'x', Items.emerald);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customBoots), "x x","x x", 'x', Items.emerald);
        //
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customAxe), "xx","xy","y", 'x', Items.emerald, 'y', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customPickaxe), "xxx"," y "," y ", 'x', Items.emerald, 'y', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(ItemManager.customShovel), "x","y","y", 'x', Items.emerald, 'y', Items.stick);
        RenderingRegistry.registerEntityRenderingHandler(CustomMob.class, new RenderCustomBiped(new ModelBiped(), 1.0f));
        if(event.getSide() == Side.CLIENT){
        	RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        	renderItem.getItemModelMesher().register(ItemManager.customItem, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customItem.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customSword, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customSword.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customHoe, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customHoe.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customBoots, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customBoots.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customChestplate, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customChestplate.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customHelmet, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customHelmet.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customLeggings, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customLeggings.name, "inventory"));
        	//
        	renderItem.getItemModelMesher().register(ItemManager.customAxe, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customAxe.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customPickaxe, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customPickaxe.name, "inventory"));
        	renderItem.getItemModelMesher().register(ItemManager.customShovel, 0, new ModelResourceLocation(this.MODID +":"+ItemManager.customShovel.name, "inventory"));
        	//
        	renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockManager.customBlock), 0, new ModelResourceLocation(this.MODID + ":" + BlockManager.customBlock.name, "inventory"));
        	
        	
        }
    }
    
    public static void createEntity(Class entityClass, String entityName, int solidColor, int sportColor){
    	int entityID = EntityRegistry.findGlobalUniqueEntityId();
    	EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
    	EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, solidColor, sportColor));
    	
    	
    }
}
