package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

public class AirShard extends Shard {
	public AirShard() {
		super(Auras.AIR);
		setUnlocalizedName(Names.Items.AIRSHARD);
		setTextureName(Reference.MODID + ":AirShard");
	}
}
