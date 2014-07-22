package com.auramcraft.inventory;

import com.auramcraft.tileentity.TileEntityAuramcraft;
import net.minecraft.inventory.InventoryCraftResult;

public class SyncedInventoryCraftResult extends InventoryCraftResult {
	private TileEntityAuramcraft entity;
	private int slot;
	
	public SyncedInventoryCraftResult(TileEntityAuramcraft entity, int slot) {
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
