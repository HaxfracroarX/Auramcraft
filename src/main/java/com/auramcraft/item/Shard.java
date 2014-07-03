package com.auramcraft.item;

import net.minecraft.item.Item;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;

public class Shard extends IAuraContainer {
	private static int maxAura = 8;
	private Auras type;
	
	public Shard(Auras type) {
		super(maxAura, 1);
		store(type, maxAura);
		this.type = type;
		this.setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		return aura.equals(type);
	}
	
	public Auras getType() {
		return type;
	}
}
