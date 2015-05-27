package com.auramcraft.item;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

public class AirShard extends Shard {
	public AirShard() {
		super(Auras.AIR);
		setUnlocalizedName(Names.Items.AIRSHARD);
		setTextureName(Textures.Items.ITEM_AIR_SHARD);
	}
}
