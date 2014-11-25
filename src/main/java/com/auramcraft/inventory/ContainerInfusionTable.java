package com.auramcraft.inventory;

import com.auramcraft.Auramcraft;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.util.LogHelper;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
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
	private TileInfusionTable tileEntity;
	private World worldObj;
	private InfusionCrafting matrix;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntity = (TileInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		
		tileEntity.openInventory();
		
		// Setup matrix
		AuraContainer auraContainer;
		AuraSlot auraSlot = new AuraSlot(tileEntity, 9, 156, 24);
		
		if(tileEntity.getStackInSlot(9) != null)
			auraContainer = AuraItem.getAuraContainer(tileEntity.getStackInSlot(9));
		else
			auraContainer = new AuraContainer(0, 0);
		
		matrix = new InfusionCrafting(this, 3, 3, auraContainer.getMaxAura(), auraContainer.getTier(), tileEntity, auraSlot);
		matrix.setAuraContainer(auraContainer);
		
		// Add the AuraItem
		addSlotToContainer(auraSlot);
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				addSlotToContainer(new Slot(matrix, j + i * 3, 6 + i * 19, 3 + j * 19));
		}
		
		// Add output slot
		addSlotToContainer(new InfusionSlotCrafting(tileEntity, 10, 102, 24, matrix));
		
		// Add Player inventory
		bindPlayerInventory(inventoryPlayer);
		
		// Check for matching recipies
		if(tileEntity.getStackInSlot(9) != null)
			onCraftMatrixChanged(matrix);
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
		tileEntity.setInventorySlotContents(10, AuramcraftCraftingManager.getInstance().findMatchingRecipe((InfusionCrafting) inventory, worldObj));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileEntity.isUseableByPlayer(entityPlayer);
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
		tileEntity.closeInventory();
	}
}
