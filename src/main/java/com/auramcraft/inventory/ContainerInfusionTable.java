package com.auramcraft.inventory;

import com.auramcraft.tileentity.TileEntityInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class ContainerInfusionTable extends Container {
	private TileEntityInfusionTable tileEntityInfusionTable;
	private InventoryPlayer ip;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, TileEntityInfusionTable tileEntityInfusionTable) {
		this.tileEntityInfusionTable = tileEntityInfusionTable;
		this.ip = inventoryPlayer;
		tileEntityInfusionTable.openInventory();
		
		// Add Output slot
		addSlotToContainer(new SlotCrafting(inventoryPlayer.player,tileEntityInfusionTable, tileEntityInfusionTable, 0, 102, 24));
		
		// Add the unknown slot
		addSlotToContainer(new Slot(tileEntityInfusionTable, 1, 156, 24));
		
		// Add Infusion Table crafting slots
		int rep = 2;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntityInfusionTable, rep, i * 19 + 6, (3 - j) + j * 20));
				rep++;
			}
		}
		
		// Add the Player's inventory
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + j * 19, 96 + i * 19));
            }
        }
		
		// Add the action slots
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 11 + i * 19, 158));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot) {
		return null;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
		tileEntityInfusionTable.closeInventory();
	}
}
