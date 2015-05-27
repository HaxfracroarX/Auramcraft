package com.auramcraft.item;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

public class AuramShard extends Shard {
	public AuramShard() {
		super(Auras.AURAM);
		setUnlocalizedName(Names.Items.AURAMSHARD);
		setTextureName(Textures.Items.ITEM_AURAM_SHARD);
	}
}
