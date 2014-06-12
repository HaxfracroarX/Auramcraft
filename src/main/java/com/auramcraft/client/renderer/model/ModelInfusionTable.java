package com.auramcraft.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInfusionTable extends ModelBase {
	// fields
	ModelRenderer Top;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	
	public ModelInfusionTable() {
		textureWidth = 64;
		textureHeight = 64;
		
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(0F, 0F, 0F, 16, 9, 16);
		Top.setRotationPoint(-8F, 8F, -8F);
		Top.setTextureSize(128, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 12, 35);
		Shape1.addBox(0F, 0F, 0F, 3, 7, 3);
		Shape1.setRotationPoint(5F, 17F, -8F);
		Shape1.setTextureSize(128, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 25);
		Shape2.addBox(0F, 0F, 0F, 3, 7, 3);
		Shape2.setRotationPoint(-8F, 17F, 5F);
		Shape2.setTextureSize(128, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 12, 25);
		Shape3.addBox(0F, 0F, 0F, 3, 7, 3);
		Shape3.setRotationPoint(5F, 17F, 5F);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 35);
		Shape4.addBox(0F, 0F, 0F, 3, 7, 3);
		Shape4.setRotationPoint(-8F, 17F, -8F);
		Shape4.setTextureSize(128, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Top.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
}
