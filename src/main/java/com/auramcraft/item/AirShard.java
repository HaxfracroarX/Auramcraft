package com.auramcraft.item;

import net.minecraft.item.Item;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.*;

public class AirShard extends Item {
	private int storedAura;
	
	public void setStoredAura(int storedAura) {
		this.storedAura = storedAura;
		this.setCreativeTab(Auramcraft.auramcraftTab);
	}
	
	public int getStoredAura() {
		return storedAura;
	}
}
