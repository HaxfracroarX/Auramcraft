package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import com.auramcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerInfusionTable extends Container {
	private TileEntityInfusionTable tileEntityInfusionTable;
	private World worldObj;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		
		// Open inventory
		tileEntityInfusionTable.openInventory();
		
		/*// Adding the player's hotbar
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 11 + i * 19, 158));
        
		// Adding the player's inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++)
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + j * 19, 96 + i * 19));
		}*/
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				addSlotToContainer(new Slot(tileEntityInfusionTable, j + i * 3, 6 + i * 19, 3 + j * 19));
		}
		
		// Add the AuraItem
		addSlotToContainer(new AuraSlot(tileEntityInfusionTable, 9, 156, 24));
		
		// Add output slot
		addSlotToContainer(new InfusionSlotCrafting(tileEntityInfusionTable, 10, 102, 24));
        
		// Add Player inventory
		bindPlayerInventory(inventoryPlayer);
		
        // Check for matching recipies
        onCraftMatrixChanged(tileEntityInfusionTable.getCraftingMatrix(this));
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Add Player Inventory
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}
		
		// Add Player Hotbar
		for(int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		tileEntityInfusionTable.setInventorySlotContents(10, AuramcraftCraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) inventory, worldObj));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileEntityInfusionTable.isUseableByPlayer(entityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot) {
		return null;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		tileEntityInfusionTable.closeInventory();
	}
}
