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
		
		// Add Infusion Table crafting slots
		int rep = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntityInfusionTable, rep, i * 18 + 7, 7 + j * 18));
				rep++;
			}
		}
		
		// Add the Player's inventory
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
		
		// Add the action slots
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
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
