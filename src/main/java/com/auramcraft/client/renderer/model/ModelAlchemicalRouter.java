package com.auramcraft.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SuppressWarnings("SameParameterValue") public class ModelAlchemicalRouter extends ModelBase {
	// fields
	private final ModelRenderer Router;
	
	public ModelAlchemicalRouter() {
		textureWidth = 64;
		textureHeight = 64;
		
		Router = new ModelRenderer(this, 0, 0);
		Router.addBox(0F, 0F, 0F, 10, 10, 10);
		Router.setRotationPoint(-5F, 11F, -5F);
		Router.setTextureSize(64, 64);
		Router.mirror = true;
		setRotation(Router, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Router.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
