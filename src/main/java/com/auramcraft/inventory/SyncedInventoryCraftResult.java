package com.auramcraft.inventory;

import com.auramcraft.tileentity.TileEntityAuramcraft;
import com.auramcraft.tileentity.TileEntityAuramcraftInventory;
import net.minecraft.inventory.InventoryCraftResult;

public class SyncedInventoryCraftResult extends InventoryCraftResult {
	private TileEntityAuramcraftInventory entity;
	private int slot;
	
	public SyncedInventoryCraftResult(TileEntityAuramcraftInventory entity, int slot) {
		this.entity = entity;
		this.slot = slot;
	}
	
	@Override
	public void openInventory() {
		setInventorySlotContents(0, entity.getStackInSlot(slot));
	}
	
	@Override
	public void closeInventory() {
		entity.setInventorySlotContents(slot, getStackInSlot(0));
	}
}
