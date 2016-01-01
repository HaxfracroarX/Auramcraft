package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.tileentity.TileInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class AuraSlot extends Slot {
	private final Container eventHandler;
	private final boolean isInput;
	
	public AuraSlot(TileInventory tileEntity, int id, int x, int y, Container eventHandler, boolean isInput) {
		super(tileEntity, id, x, y);
		this.eventHandler = eventHandler;
		this.isInput = isInput;
	}
	
	@Override
	public void onSlotChanged() {
		eventHandler.onCraftMatrixChanged(null);
		super.onSlotChanged();
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		if(!(itemStack.getItem() instanceof AuraItem && itemStack.stackSize == 1))
			return false;
		if(isInput && AuraItem.getAuraContainer(itemStack).isDrainable())
			return true;
		else if(!isInput && AuraItem.getAuraContainer(itemStack).isFillable())
			return true;
		return false;
    }
	
	public AuraContainer getItemAuraContainer() {
		return AuraItem.getAuraContainer(getStack());
	}
}
