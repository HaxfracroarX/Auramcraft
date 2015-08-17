package com.auramcraft.tileentity;

import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MsgTileAuramcraft;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

@SuppressWarnings("WeakerAccess")
public class TileAuramcraft extends TileEntity {
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
		setOrientation(ForgeDirection.getOrientation(orientation));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		// Read orientation
		setOrientation(nbtTagCompound.getInteger("teDirection"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// Write orientation
		nbtTagCompound.setInteger("teDirection", orientation.ordinal());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.wrapper.getPacketFrom(new MsgTileAuramcraft(this));
	}
}
