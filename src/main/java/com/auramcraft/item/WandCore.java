package com.auramcraft.item;

import net.minecraft.item.Item;

public class WandCore extends Item {
	public String texture;
	
	public WandCore(String texture) {
		this.texture = texture;
		setTextureName(texture);
	}
}
