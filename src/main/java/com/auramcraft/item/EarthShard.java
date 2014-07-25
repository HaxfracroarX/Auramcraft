package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.api.Auras;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;

public class EarthShard extends Shard {
	public EarthShard() {
		super(Auras.EARTH);
		setUnlocalizedName(Names.Items.EARTHSHARD);
		setTextureName(Textures.Items.ITEM_EARTH_SHARD);
	}
}
