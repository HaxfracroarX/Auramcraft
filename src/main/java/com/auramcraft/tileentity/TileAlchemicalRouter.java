package com.auramcraft.tileentity;

import net.minecraft.block.Block;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;

public class TileAlchemicalRouter extends TileAuramcraftAuraUser {
	public TileAlchemicalRouter() {
		super(Names.Blocks.ALCHEMICALROUTER, AuramcraftBlocks.alchemicalRouter, new AuraContainer(5, Tiers.getTotalTiers()));
	}
}
