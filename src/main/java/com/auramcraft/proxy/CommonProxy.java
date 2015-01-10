package com.auramcraft.proxy;

import net.minecraftforge.common.MinecraftForge;
import com.auramcraft.Auramcraft;
import com.auramcraft.handler.AuramcraftEventHandler;
import com.auramcraft.handler.GuiHandler;
import com.auramcraft.reference.Names;
import com.auramcraft.tileentity.TileAlchemicalRouter;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.tileentity.TileStorageJar;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {
	@Override
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileInfusionTable.class, "tile." + Names.Blocks.INFUSIONTABLE);
		GameRegistry.registerTileEntity(TileStorageJar.class, "tile." + Names.Blocks.STORAGEJAR);
		GameRegistry.registerTileEntity(TileAlchemicalRouter.class, "tile." + Names.Blocks.ALCHEMICALROUTER);
	}
	
	@Override
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new AuramcraftEventHandler());
		FMLCommonHandler.instance().bus().register(new AuramcraftEventHandler());
	}
	
	@Override
	public void registerGUIHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Auramcraft.instance, new GuiHandler());
	}

	@Override
	public void registerRenderers() {
		// Do nothing
	}
}
