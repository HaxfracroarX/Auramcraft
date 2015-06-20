package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Names;

public class TileAuraConverter extends TileAuramcraftAuraUserInventory {
	public TileAuraConverter() {
		super(Names.Blocks.AURA_CONVERTER, AuramcraftBlocks.auraConverter, 2, new AuraContainer(100, 1));
	}
}
