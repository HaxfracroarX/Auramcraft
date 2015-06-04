package com.auramcraft.client.gui.inventory;

import net.minecraft.util.ResourceLocation;

@SuppressWarnings("WeakerAccess")
public class BookPage {
	private final ResourceLocation data;
	private final int id;
	
	public BookPage(ResourceLocation data, int id) {
		this.data = data;
		this.id = id;
	}
	
	public ResourceLocation getData() {
		return data;
	}
	
	public int getID() {
		return id;
	}
}
