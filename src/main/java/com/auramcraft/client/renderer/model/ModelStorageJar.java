package com.auramcraft.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SuppressWarnings("SameParameterValue") public class ModelStorageJar extends ModelBase {
	// fields
	private final ModelRenderer Lid;
	private final ModelRenderer Base;
	private final ModelRenderer Wall1;
	private final ModelRenderer Wall2;
	private final ModelRenderer Wall3;
	private final ModelRenderer Wall4;
	private final ModelRenderer Cap;
	
	public ModelStorageJar() {
		textureWidth = 64;
		textureHeight = 64;
		
		Lid = new ModelRenderer(this, 0, 16);
		Lid.addBox(0F, 0F, 0F, 8, 1, 8);
		Lid.setRotationPoint(-4F, 11F, -4F);
		Lid.setTextureSize(64, 64);
		Lid.mirror = true;
		setRotation(Lid, 0F, 0F, 0F);
		Base = new ModelRenderer(this, 24, 0);
		Base.addBox(0F, 0F, 0F, 10, 1, 10);
		Base.setRotationPoint(-5F, 23F, -5F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Wall1 = new ModelRenderer(this, 0, 32);
		Wall1.addBox(0F, 0F, 0F, 10, 12, 1);
		Wall1.setRotationPoint(-5F, 11F, 4F);
		Wall1.setTextureSize(64, 64);
		Wall1.mirror = true;
		setRotation(Wall1, 0F, 0F, 0F);
		Wall2 = new ModelRenderer(this, 0, 32);
		Wall2.addBox(0F, 0F, 0F, 10, 12, 1);
		Wall2.setRotationPoint(-5F, 11F, -5F);
		Wall2.setTextureSize(64, 64);
		Wall2.mirror = true;
		setRotation(Wall2, 0F, 0F, 0F);
		Wall3 = new ModelRenderer(this, 32, 32);
		Wall3.addBox(0F, 0F, 0F, 1, 12, 8);
		Wall3.setRotationPoint(-5F, 11F, -4F);
		Wall3.setTextureSize(64, 64);
		Wall3.mirror = true;
		setRotation(Wall3, 0F, 0F, 0F);
		Wall4 = new ModelRenderer(this, 32, 32);
		Wall4.addBox(0F, 0F, 0F, 1, 12, 8);
		Wall4.setRotationPoint(4F, 11F, -4F);
		Wall4.setTextureSize(64, 64);
		Wall4.mirror = true;
		setRotation(Wall4, 0F, 0F, 0F);
		Cap = new ModelRenderer(this, 0, 0);
		Cap.addBox(0F, 0F, 0F, 4, 2, 4);
		Cap.setRotationPoint(-2F, 9F, -2F);
		Cap.setTextureSize(64, 64);
		Cap.mirror = true;
		setRotation(Cap, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Lid.render(f5);
		Base.render(f5);
		Wall1.render(f5);
		Wall2.render(f5);
		Wall3.render(f5);
		Wall4.render(f5);
		Cap.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
