package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.item.AuraItem;
import com.auramcraft.tileentity.TileAuraConverter;
import com.auramcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;

public class ContainerAuraConverter extends Container {
	TileAuraConverter tileEntity;
	
	public ContainerAuraConverter(InventoryPlayer inventoryPlayer, final World world, int x, int y, int z) {
		tileEntity = (TileAuraConverter) world.getTileEntity(x, y, z);
		
		tileEntity.openInventory();
		
		// Create Slots
		AuraSlot input = new AuraSlot(tileEntity, 0, 49, 34, this, true) {
			@Override
			public void putStack(ItemStack itemStack) {
				super.putStack(itemStack);
				
				if(world.isRemote || itemStack == null)
					return;
				
				// Set input and output
				
				// Make list of random auras
				Auras[] auras = Auras.values();
				ArrayList<Integer> randList = new ArrayList<Integer>();
				randList.add(0);
				randList.add(1);
				randList.add(2);
				randList.add(3);
				randList.add(4);
				Collections.shuffle(randList);
				Auras[] randomAuras = new Auras[5];
				for(int i = 0; i < randomAuras.length; i++)
					randomAuras[i] = auras[randList.get(i)];
				
				// Check if there's an available input
				int inputID = -1;
				AuraContainer inputContainer = AuraItem.getAuraContainer(itemStack);
				for(int i = 0; i < randomAuras.length; i++) {
					boolean suitable = inputContainer.containsAura(randomAuras[i]);
					
					// If there's already an output, we have to take that into account
					if(tileEntity.getOutput() != null)
						suitable = suitable && randomAuras[i] != tileEntity.getOutput();
					
					if(suitable) {
						inputID = i;
						break;
					}
				}
				
				if(inputID == -1)
					return;
				
				LogHelper.info("Input: " + auras[inputID]);
				
				tileEntity.setInput(auras[inputID]);
				tileEntity.sendPacket();
			}
		};
		
		final AuraSlot output = new AuraSlot(tileEntity, 1, 125, 34, this, false) {
			@Override
			public void putStack(ItemStack itemStack) {
				super.putStack(itemStack);
				
				if(world.isRemote || itemStack == null)
					return;
				
				// Set input and output
				
				// Make list of random auras
				Auras[] auras = Auras.values();
				ArrayList<Integer> randList = new ArrayList<Integer>();
				randList.add(0);
				randList.add(1);
				randList.add(2);
				randList.add(3);
				randList.add(4);
				Collections.shuffle(randList);
				Auras[] randomAuras = new Auras[5];
				for(int i = 0; i < randomAuras.length; i++)
					randomAuras[i] = auras[randList.get(i)];
				
				// Get output aura
				int outputID = -1;
				AuraContainer outputContainer = AuraItem.getAuraContainer(itemStack);
				for(int i = 0; i < randomAuras.length; i++) {
					boolean suitable = outputContainer.canStoreAura(randomAuras[i]);
					
					// If there's already an input, we have to take that into account
					if(tileEntity.getInput() != null)
						suitable = suitable && randomAuras[i] != tileEntity.getInput();
					
					if(suitable) {
						outputID = i;
						break;
					}
				}
				
				if(outputID == -1)
					return;
				
				LogHelper.info("Output: " + auras[outputID]);
				
				tileEntity.setOutput(auras[outputID]);
				tileEntity.sendPacket();
			}
		};
		
		// Add Slots
		addSlotToContainer(input);
		addSlotToContainer(output);
		
		// Add Player inventory
		bindPlayerInventory(inventoryPlayer);
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        
        // Checks if null and if there is a stack
        if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			
			stack = stackInSlot.copy();
			
			// Checks if the slot is in the tileEntity
			if(slot < 2) {
				// Attempts to merge the item into the player inventory
				if(!this.mergeItemStack(stackInSlot, 2, 38, true))
					return null;
			}
			// Attempts to merge the item into the tileEntity
			else if(!this.mergeItemStack(stackInSlot, 0, 2, false))
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
		
		// Check for an existing stack of the item of itemStack in order to add itself to it
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
		
		// Check for an empty slot to fill with the remaining items in itemStack
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
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
}
