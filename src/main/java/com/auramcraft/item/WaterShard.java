package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

public class WaterShard extends Shard {
	public WaterShard() {
		super(Auras.WATER);
		setUnlocalizedName(Names.Items.WATERSHARD);
		setTextureName(Reference.MODID + ":WaterShard");
	}
}
