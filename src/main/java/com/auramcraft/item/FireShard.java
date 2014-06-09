package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

public class FireShard extends Shard {
	public FireShard() {
		super(Auras.FIRE);
		setUnlocalizedName(Names.Items.FIRESHARD);
		setTextureName(Reference.MODID + ":FireShard");
	}
}
