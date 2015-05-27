package com.auramcraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class TileAuramcraftInventory extends TileAuramcraft implements IInventory {
	private ItemStack[] inventory;
	
	public TileAuramcraftInventory(String name, Block block, int slots) {
		super(name, block);
		inventory = new ItemStack[slots];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		// Read inventory
		NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
		inventory = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			byte slotIndex = tagCompound.getByte("Slot");
			if(slotIndex >= 0 && slotIndex < inventory.length)
				inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// Write inventory
		NBTTagList tagList = new NBTTagList();
		for(int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if(inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		
		nbtTagCompound.setTag("Items", tagList);
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
		return name;
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
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, block, 1, numPlayersOpen);
	}
	
	@Override
	public void closeInventory() {
		numPlayersOpen--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, block, 1, numPlayersOpen);
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
}
