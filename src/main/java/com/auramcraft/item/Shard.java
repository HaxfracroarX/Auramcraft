package com.auramcraft.item;

import com.auramcraft.api.Auras;
import com.auramcraft.creativetab.CreativeTab;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class Shard extends AuraItem {
	private static final int maxAura = 8;
	
	public Shard(Auras type) {
		super(maxAura, 1);
		getAuraContainer().store(type, maxAura);
		getAuraContainer().addAllowed(type);
		getAuraContainer().setDrainable(false);
		getAuraContainer().setFillable(false);
		this.setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
