package com.auramcraft.tileentity;

import com.auramcraft.Auramcraft;
import com.auramcraft.init.AuramcraftBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInfusionTable extends TileEntity implements IInventory {
	private ItemStack[] inventory;
	private int numPlayersOpen;
	
	public TileEntityInfusionTable() {
		super();
		inventory = new ItemStack[11];
		numPlayersOpen = 0;
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null) {
			if(stack.stackSize <= amount)
				setInventorySlotContents(slot, null);
			else {
				stack = stack.splitStack(amount);
				if(stack.stackSize == 0)
					setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if(inventory[slot] != null) {
			ItemStack stack = inventory[slot];
			inventory[slot] = null;
			return stack;
		}
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}
	
	@Override
	public String getInventoryName() {
		return "Infusion Table";
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return true;
	}
	
	@Override
	public void openInventory() {
		numPlayersOpen++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, AuramcraftBlocks.infusionTable, 1, numPlayersOpen);
	}
	
	@Override
	public void closeInventory() {
		numPlayersOpen--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, AuramcraftBlocks.infusionTable, 1, numPlayersOpen);
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
}
