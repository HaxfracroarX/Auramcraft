package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AuraSlot extends Slot {
	private Container eventHandler;
	
	public AuraSlot(TileAuramcraftInventory inventory, int id, int x, int y, Container eventHandler) {
		super(inventory, id, x, y);
		this.eventHandler = eventHandler;
	}
	
	@Override
	public void onSlotChanged() {
		eventHandler.onCraftMatrixChanged(null);
		super.onSlotChanged();
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof AuraItem && itemStack.stackSize == 1 && AuraItem.getAuraContainer(itemStack).isDrainable();
    }
}
