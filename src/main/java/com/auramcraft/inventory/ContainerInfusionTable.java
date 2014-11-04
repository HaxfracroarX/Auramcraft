package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileInfusionTable;
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
	private TileInfusionTable tileInfusionTable;
	private World worldObj;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileInfusionTable = (TileInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		
		tileInfusionTable.openInventory();
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				addSlotToContainer(new Slot(tileInfusionTable, j + i * 3, 6 + i * 19, 3 + j * 19));
		}
		
		// Add Player inventory
		bindPlayerInventory(inventoryPlayer);
		
		// Add the AuraItem
		addSlotToContainer(new AuraSlot(tileInfusionTable, 9, 156, 24));
		
		// Add output slot
		addSlotToContainer(new InfusionSlotCrafting(tileInfusionTable, 10, 102, 24));
		
        // Check for matching recipies
        onCraftMatrixChanged(tileInfusionTable.getCraftingMatrix(this));
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Add Player Inventory
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + j * 19, 96 + i * 19));
		}
		
		// Add Player Hotbar
		for(int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventoryPlayer, i, 11 + i * 19, 158));
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		tileInfusionTable.setInventorySlotContents(10, AuramcraftCraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) inventory, worldObj));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileInfusionTable.isUseableByPlayer(entityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            //merges the item into player inventory since its in the tileEntity
            if (slot < 9) {
                if (!this.mergeItemStack(stackInSlot, 0, 35, true))
                    return null;
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, 9, false))
                return null;
            
            if (stackInSlot.stackSize == 0)
                slotObject.putStack(null);
            else
                slotObject.onSlotChanged();
            
            if (stackInSlot.stackSize == stack.stackSize)
                return null;
            
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		tileInfusionTable.closeInventory();
	}
}
