package com.auramcraft.network;

import com.auramcraft.network.message.MessageTileAuramcraft;
import com.auramcraft.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public static void init() {
		INSTANCE.registerMessage(MessageTileAuramcraft.class, MessageTileAuramcraft.class, 0, Side.CLIENT);
	}
}
