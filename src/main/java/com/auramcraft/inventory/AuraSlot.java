package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AuraSlot extends Slot {
	private SyncedInfusionCrafting craftMatrix;
	private SyncedInventoryCraftResult craftResult;
	private World worldObj;
	
	public AuraSlot(SyncedInventory inventory, SyncedInfusionCrafting craftMatrix, SyncedInventoryCraftResult craftResult, World worldObj, int id, int x, int y) {
		super(inventory, id, x, y);
		this.craftMatrix = craftMatrix;
		this.craftResult = craftResult;
		this.worldObj = worldObj;
	}
	
	public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof AuraItem && itemStack.stackSize == 1 && AuraItem.getAuraContainer(itemStack).isDrainable();
    }
	
	public void onSlotChanged() {
		if(getStack() != null)
			craftMatrix.setAuraContainer(AuraItem.getAuraContainer(getStack()));
		else
			craftMatrix.setAuraContainer(new AuraContainer(0, 0));
		inventory.closeInventory();
		craftResult.setInventorySlotContents(0, AuramcraftCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
    }
}
