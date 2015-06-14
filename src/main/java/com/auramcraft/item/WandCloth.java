package com.auramcraft.item;

import net.minecraft.item.Item;

public class WandCloth extends Item {
	public String texture;
	
	public WandCloth(String texture) {
		this.texture = texture;
		setTextureName(texture);
	}
}
