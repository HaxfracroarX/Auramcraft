package com.auramcraft.tileentity;

import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MessageTileEntityAuramcraft;
import com.auramcraft.reference.Names;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAuramcraft extends TileEntity {
	protected ForgeDirection orientation;
	
	public TileEntityAuramcraft() {
		orientation = ForgeDirection.SOUTH;
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
		
		if(nbtTagCompound.hasKey(Names.NBT.DIRECTION))
			setOrientation(ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.DIRECTION)));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte) orientation.ordinal());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAuramcraft(this));
	}
}
