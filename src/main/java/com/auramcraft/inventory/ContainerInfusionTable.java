package com.auramcraft.inventory;

import com.auramcraft.tileentity.TileEntityInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerInfusionTable extends Container {
	private TileEntityInfusionTable tileEntityInfusionTable;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, TileEntityInfusionTable tileEntityInfusionTable) {
		this.tileEntityInfusionTable = tileEntityInfusionTable;
		tileEntityInfusionTable.openInventory();
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntityInfusionTable, i + j, i * 18 + 8, j * 18 + 8));
			}
		}
		
		// Add Output slot
		addSlotToContainer(new Slot(tileEntityInfusionTable, 9, 100, 20));
		
		// Add the Player's inventory
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++)
            {
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
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
		tileEntityInfusionTable.closeInventory();
	}
}
