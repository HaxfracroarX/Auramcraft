package com.auramcraft.inventory;

import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerInfusionTable extends Container {
	private final TileInfusionTable tileEntity;
	private final World worldObj;
	private final InfusionCrafting matrix;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntity = (TileInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		
		tileEntity.openInventory();
		
		// Setup matrix
		AuraSlot auraSlot = new AuraSlot(tileEntity, 9, 156, 24, this);
		
		matrix = new InfusionCrafting(this, 3, 3, tileEntity, auraSlot);
		matrix.init();
		
		// Add AuraItem
		addSlotToContainer(auraSlot);
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				addSlotToContainer(new Slot(matrix, j + i * 3, 6 + i * 19, 3 + j * 19));
		}
		
		// Add output slot
		addSlotToContainer(new InfusionSlotCrafting(tileEntity, 10, 102, 24, matrix, this));
		
		// Add Player inventory
		bindPlayerInventory(inventoryPlayer);
		
		// Check for matching recipes
		onCraftMatrixChanged(null);
	}
	
	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
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
		tileEntity.setInventorySlotContents(10, AuramcraftCraftingManager.getInstance().findMatchingRecipe(matrix, worldObj));
		super.onCraftMatrixChanged(inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileEntity.isUseableByPlayer(entityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        
        // Checks if null and if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			// Checks if the slot is in the tileEntity
			if(slot < 11) {
				// Attempts to merge the item into the player inventory
				if(!this.mergeItemStack(stackInSlot, 11, 46, true))
					return null;
			}
			// Attempts to merge the item into the tileEntity
			else if(!this.mergeItemStack(stackInSlot, 0, 10, false))
					return null;
			
			// If empty, replaces stack with null
            if (stackInSlot.stackSize == 0)
                slotObject.putStack(null);
			else
                slotObject.onSlotChanged();
			
			// Checks that the merge was successful
            if (stackInSlot.stackSize == stack.stackSize)
                return null;
            
            slotObject.onPickupFromSlot(player, stackInSlot);
			onCraftMatrixChanged(null);
        }
		
        return stack;
	}
	
	/**
	 * @param itemStack ItemStack to merge into inventory
	 * @param start minimum slot to attempt fill
	 * @param end maximum slot to attempt fill
	 * @param backwards go backwards
	 * @return true if stacks merged successfully
	 */
	@Override
	protected boolean mergeItemStack(ItemStack itemStack, int start, int end, boolean backwards) {
		boolean isMerged = false;
		int current = start;
		
		if (backwards)
			current = end - 1;
		
		Slot slot;
		ItemStack itemStackInSlot;
		
		if (itemStack.isStackable()) {
			while (itemStack.stackSize > 0 && (!backwards && current < end || backwards && current >= start)) {
				slot = (Slot)this.inventorySlots.get(current);
				itemStackInSlot = slot.getStack();
				
				if (itemStackInSlot != null && 
						itemStackInSlot.getItem() == itemStack.getItem() && 
						(!itemStack.getHasSubtypes() || itemStack.getItemDamage() == itemStackInSlot.getItemDamage()) && 
						ItemStack.areItemStackTagsEqual(itemStack, itemStackInSlot)) {
					int totalSize = itemStackInSlot.stackSize + itemStack.stackSize;
					
					if (totalSize <= itemStack.getMaxStackSize()) {
						itemStack.stackSize = 0;
						itemStackInSlot.stackSize = totalSize;
						slot.onSlotChanged();
						isMerged = true;
					}
					else if (itemStackInSlot.stackSize < itemStack.getMaxStackSize()) {
						itemStack.stackSize -= itemStack.getMaxStackSize() - itemStackInSlot.stackSize;
						itemStackInSlot.stackSize = itemStack.getMaxStackSize();
						slot.onSlotChanged();
						isMerged = true;
					}
				}
				
				if (backwards)
					--current;
				else
					++current;
			}
		}
		
		if (itemStack.stackSize > 0) {
			if (backwards)
				current = end - 1;
			else
				current = start;
			
			while (!backwards && current < end || backwards && current >= start) {
				slot = (Slot)this.inventorySlots.get(current);
				itemStackInSlot = slot.getStack();
				
				if (itemStackInSlot == null && slot.isItemValid(itemStack)) {
					slot.putStack(itemStack.copy());
					slot.onSlotChanged();
					itemStack.stackSize = 0;
					isMerged = true;
					break;
				}
				
				if (backwards)
					--current;
				else
					++current;
			}
		}
		
		return isMerged;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		tileEntity.closeInventory();
	}
}
