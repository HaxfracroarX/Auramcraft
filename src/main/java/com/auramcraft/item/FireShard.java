package com.auramcraft.item;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

public class FireShard extends Shard {
	public FireShard() {
		super(Auras.FIRE);
		setUnlocalizedName(Names.Items.FIRESHARD);
		setTextureName(Textures.Items.FIRE_SHARD);
	}
}
