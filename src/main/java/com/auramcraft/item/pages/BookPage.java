package com.auramcraft.item.pages;

import com.auramcraft.reference.Textures;
import net.minecraft.util.ResourceLocation;

public class BookPage {
	ResourceLocation texture;
	
	public BookPage(ResourceLocation texture) {
		this.texture = texture;
	}
	
	public ResourceLocation getTexture() {
		return texture;
	}
}
