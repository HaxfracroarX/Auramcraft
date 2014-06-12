package com.auramcraft.network.message;

import net.minecraft.tileentity.TileEntity;
import com.auramcraft.tileentity.TileEntityAuramcraft;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityAuramcraft implements IMessage, IMessageHandler<MessageTileEntityAuramcraft, IMessage> {
	public int x, y, z;
	public byte orientation;
	
	public MessageTileEntityAuramcraft() {}
	
	public MessageTileEntityAuramcraft(TileEntityAuramcraft tileEntity) {
		this.x = tileEntity.xCoord;
		this.y = tileEntity.yCoord;
		this.z = tileEntity.zCoord;
		this.orientation = (byte) tileEntity.getOrientation().ordinal();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.orientation = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(orientation);
	}
	
	@Override
	public IMessage onMessage(MessageTileEntityAuramcraft message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		
		if(tileEntity instanceof TileEntityAuramcraft) {
			((TileEntityAuramcraft) tileEntity).setOrientation(message.orientation);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("MessageTileEntityAuramcraft - x:%s, y:%s, z:%s, orientation:%s", x, y, z, orientation);
	}
}
