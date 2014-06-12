package com.auramcraft.proxy;

import com.auramcraft.client.renderer.tileentity.TileEntityInfusionTableRenderer;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfusionTable.class, new TileEntityInfusionTableRenderer());
	}
}
