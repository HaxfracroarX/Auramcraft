package com.auramcraft.item.pages;

import net.minecraft.util.ResourceLocation;

@SuppressWarnings("WeakerAccess")
public class BookPage {
	private final ResourceLocation texture;
	private final int id;
	
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
