package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

public class EarthShard extends Shard {
	public EarthShard() {
		super(Auras.EARTH);
		setUnlocalizedName(Names.Items.EARTHSHARD);
		setTextureName(Reference.MODID + ":EarthShard");
	}
}
