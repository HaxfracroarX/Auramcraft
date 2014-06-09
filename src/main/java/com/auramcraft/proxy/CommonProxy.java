package com.auramcraft.proxy;

import com.auramcraft.reference.Names;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
	@Override
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInfusionTable.class, "tile." + Names.Blocks.INFUSIONTABLE);
	}
}
