package com.auramcraft.network;

import com.auramcraft.network.message.MessagePlayerStats;
import com.auramcraft.network.message.MessageTileAuramcraft;
import com.auramcraft.reference.Reference;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.tileentity.TileAuramcraft;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public static void init() {
		wrapper.registerMessage(TileAuramcraft.class, MessageTileAuramcraft.class, 0, Side.CLIENT);
		wrapper.registerMessage(AuramcraftPlayerStats.class, MessagePlayerStats.class, 1, Side.CLIENT);
		
		LogHelper.info("Initialized Packets");
	}
}
