package com.auramcraft.tileentity;

import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;

public class TileStorageJar extends TileAuramcraftAuraUser {
	public TileStorageJar() {
		super(Names.Blocks.STORAGEJAR, AuramcraftBlocks.storageJar, new AuraContainer(32, Tiers.getTotalTiers()));
	}
}
