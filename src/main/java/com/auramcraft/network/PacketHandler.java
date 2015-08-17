package com.auramcraft.network;

import com.auramcraft.network.message.MsgAuraConverter;
import com.auramcraft.network.message.MsgPlayerStats;
import com.auramcraft.network.message.MsgTileAuramcraft;
import com.auramcraft.reference.Reference;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.tileentity.TileAlchemicalRouter;
import com.auramcraft.tileentity.TileAuraConverter;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.tileentity.TileStorageJar;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	
	public static void init() {
		wrapper.registerMessage(AuramcraftPlayerStats.class, MsgPlayerStats.class, 0, Side.CLIENT);
		wrapper.registerMessage(TileInfusionTable.class, MsgTileAuramcraft.class, 1, Side.CLIENT);
		wrapper.registerMessage(TileAlchemicalRouter.class, MsgTileAuramcraft.class, 2, Side.CLIENT);
		wrapper.registerMessage(TileStorageJar.class, MsgTileAuramcraft.class, 3, Side.CLIENT);
		wrapper.registerMessage(TileAuraConverter.class, MsgAuraConverter.class, 4, Side.CLIENT);
		
		LogHelper.info("Initialized Packets");
	}
}
