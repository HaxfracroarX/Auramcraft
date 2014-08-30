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
	public SyncedInfusionCrafting craftMatrix;
    public SyncedInventoryCraftResult craftResult;
    private SyncedInventory auraItem;
	private World worldObj;
	private int x, y, z;
	private TileEntityInfusionTable tileEntityInfusionTable;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
		// Initializing synced inventories
		craftMatrix = new SyncedInfusionCrafting(this, 3, 3, 25, 1, tileEntityInfusionTable, 0);
		craftResult = new SyncedInventoryCraftResult(tileEntityInfusionTable, 9);
		auraItem = new SyncedInventory(tileEntityInfusionTable, 10);
		
		// Opening inventories
		tileEntityInfusionTable.openInventory();
		craftMatrix.openInventory();
		craftResult.openInventory();
		auraItem.openInventory();
		
		// Adding Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				addSlotToContainer(new Slot(craftMatrix, j + i * 3, i * 19 + 6, (3 - j) + j * 20));
		}
		
		// Adding the AuraItem
		AuraSlot auraSlot = new AuraSlot(auraItem, craftMatrix, craftResult, worldObj, 9, 156, 24);
		addSlotToContainer(auraSlot);
		
		// Adding output slot
		addSlotToContainer(new InfusionSlotCrafting(inventoryPlayer.player, craftMatrix, craftResult, auraItem, auraSlot, 0, 102, 24));
		
		// Adding the player's inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++)
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + j * 19, 96 + i * 19));
		}
		
		// Adding the player's hotbar
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 11 + i * 19, 158));
        
        // Checking for matching recipies
        onCraftMatrixChanged(craftMatrix);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		craftResult.setInventorySlotContents(0, AuramcraftCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
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
		craftMatrix.closeInventory();
		tileEntityInfusionTable.closeInventory();
		craftResult.closeInventory();
		auraItem.closeInventory();
	}
}
