package com.auramcraft.tileentity;

import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MessageTileAuramcraft;
import com.auramcraft.reference.Names;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

@SuppressWarnings("WeakerAccess")
public class TileAuramcraft extends TileEntity implements IMessageHandler<MessageTileAuramcraft, IMessage> {
	protected ForgeDirection orientation;
	protected int numPlayersOpen;
	protected String name;
	protected Block block;
	
	public TileAuramcraft() {}
	
	public TileAuramcraft(String name, Block block) {
		orientation = ForgeDirection.SOUTH;
		this.name = name;
		this.block = block;
		numPlayersOpen = 0;
	}
	
	public ForgeDirection getOrientation() {
		return orientation;
	}
	
	public void setOrientation(ForgeDirection orientation) {
		this.orientation = orientation;
	}
	
	public void setOrientation(int orientation) {
		this.orientation = ForgeDirection.getOrientation(orientation);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		// Read orientation
		if(nbtTagCompound.hasKey(Names.NBT.DIRECTION))
			setOrientation(ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.DIRECTION)));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// Write orientation
		nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte) orientation.ordinal());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.wrapper.getPacketFrom(new MessageTileAuramcraft(this));
	}
	
	@Override
	public IMessage onMessage(MessageTileAuramcraft message, MessageContext ctx) {
		setOrientation(message.orientation);
		return null;
	}
}
