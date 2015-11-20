package com.camp.entity;

import com.example.examplemod.ExampleMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCustomBiped extends RenderBiped {

	public RenderCustomBiped(ModelBiped model, float scale) {
		super(Minecraft.getMinecraft().getRenderManager(), model, scale);
	
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return new ResourceLocation(ExampleMod.MODID, "textures/entity/custom_biped.png");
		
		
		
	}
}