package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.network.message.MsgTileAuramcraft;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class TileAlchemicalRouter extends TileAuraUser implements IMessageHandler<MsgTileAuramcraft, IMessage> {
	public TileAlchemicalRouter() {
		super(Names.Blocks.ALCHEMICAL_ROUTER, AuramcraftBlocks.alchemicalRouter, new AuraContainer(5, Tiers.getTotalTiers()));
	}
	
	@Override
	public IMessage onMessage(MsgTileAuramcraft message, MessageContext ctx) {
		((TileAuramcraft) Minecraft.getMinecraft().theWorld
			.getTileEntity(message.x, message.y, message.z))
			.setOrientation(message.orientation);
		return null;
	}
}
