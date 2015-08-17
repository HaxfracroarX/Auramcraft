package com.auramcraft.tileentity;

import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.network.message.MsgTileAuramcraft;
import com.auramcraft.reference.Names;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class TileInfusionTable extends TileInventory implements IMessageHandler<MsgTileAuramcraft, IMessage> {
	public TileInfusionTable() {
		super(Names.Blocks.INFUSION_TABLE, AuramcraftBlocks.infusionTable, 11);
	}
	
	@Override
	public IMessage onMessage(MsgTileAuramcraft message, MessageContext ctx) {
		((TileAuramcraft) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z)).setOrientation(message.orientation);
		return null;
	}
}
