package com.auramcraft.item;

import net.minecraft.item.Item;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;

public class Shard extends AuraItem {
	private static int maxAura = 8;
	
	public Shard(Auras type) {
		super(maxAura, 1);
		getAuraContainer().store(type, maxAura);
		getAuraContainer().addAllowed(type);
		getAuraContainer().setDrainable(false);
		getAuraContainer().setFillable(false);
		this.setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
