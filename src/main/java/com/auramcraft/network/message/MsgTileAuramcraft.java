package com.auramcraft.network.message;

import com.auramcraft.tileentity.TileAuramcraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MsgTileAuramcraft implements IMessage {
	public int orientation;
	public int x, y, z;
	
	public MsgTileAuramcraft() {}
	
	public MsgTileAuramcraft(TileAuramcraft tileEntity) {
		orientation = tileEntity.getOrientation().ordinal();
		x = tileEntity.xCoord;
		y = tileEntity.yCoord;
		z = tileEntity.zCoord;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		orientation = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(orientation);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
}
