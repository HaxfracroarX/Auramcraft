package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@SuppressWarnings("SameParameterValue")
public class AuraSlot extends Slot {
	private final Container eventHandler;
	
	public AuraSlot(TileAuramcraftInventory tileEntity, int id, int x, int y, Container eventHandler) {
		super(tileEntity, id, x, y);
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
	
	public AuraContainer getItemAuraContainer() {
		return AuraItem.getAuraContainer(getStack());
	}
}
