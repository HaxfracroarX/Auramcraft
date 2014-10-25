package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AuraSlot extends Slot {
	public AuraSlot(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof AuraItem && itemStack.stackSize == 1 && AuraItem.getAuraContainer(itemStack).isDrainable();
    }
}
