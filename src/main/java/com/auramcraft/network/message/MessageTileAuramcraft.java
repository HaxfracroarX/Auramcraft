package com.auramcraft.network.message;

import com.auramcraft.tileentity.TileAuramcraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageTileAuramcraft implements IMessage {
	public byte orientation;
	
	public MessageTileAuramcraft() {}
	
	public MessageTileAuramcraft(TileAuramcraft tileEntity) {
		this.orientation = (byte) tileEntity.getOrientation().ordinal();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.orientation = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(orientation);
	}
}
