package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

public class AuramShard extends Shard {
	public AuramShard() {
		super(Auras.AURAM);
		setUnlocalizedName(Names.Items.AURAMSHARD);
		setTextureName(Reference.MODID + ":AuramShard");
	}
}
