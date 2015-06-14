package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;

public class TileStorageJar extends TileAuramcraftAuraUser {
	public TileStorageJar() {
		super(Names.Blocks.STORAGE_JAR, AuramcraftBlocks.storageJar, new AuraContainer(32, Tiers.getTotalTiers()));
	}
}
