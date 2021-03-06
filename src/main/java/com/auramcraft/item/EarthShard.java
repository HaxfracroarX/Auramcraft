package com.auramcraft.item;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

public class EarthShard extends Shard {
	public EarthShard() {
		super(Auras.EARTH);
		setUnlocalizedName(Names.Items.EARTHSHARD);
		setTextureName(Textures.Items.EARTH_SHARD);
	}
}
