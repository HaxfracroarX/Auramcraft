package com.auramcraft.item.pages;

import com.auramcraft.reference.Textures;
import net.minecraft.util.ResourceLocation;

public class BookPage {
	private ResourceLocation texture;
	private int id;
	
	public BookPage(ResourceLocation texture, int id) {
		this.texture = texture;
		this.id = id;
	}
	
	public ResourceLocation getTexture() {
		return texture;
	}
	
	public int getID() {
		return id;
	}
}
