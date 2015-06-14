package com.auramcraft.item;

import net.minecraft.item.Item;

public class WandCap extends Item {
	public String texture;
	
	public WandCap(String texture) {
		this.texture = texture;
		setTextureName(texture);
	}
}
