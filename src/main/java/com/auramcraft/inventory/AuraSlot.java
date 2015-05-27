package com.auramcraft.inventory;

import com.auramcraft.item.AuraItem;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@SuppressWarnings("SameParameterValue") public class AuraSlot extends Slot {
	private final Container eventHandler;
	
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
