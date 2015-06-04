package com.auramcraft.network.message;

import com.auramcraft.tileentity.TileAuramcraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageTileAuramcraft implements IMessage {
	public int orientation;
	public int x, y, z;
	
	public MessageTileAuramcraft() {}
	
	public MessageTileAuramcraft(TileAuramcraft tileEntity) {
		this.orientation = tileEntity.getOrientation().ordinal();
		this.x = tileEntity.xCoord;
		this.y = tileEntity.yCoord;
		this.z = tileEntity.zCoord;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.orientation = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(orientation);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
}
