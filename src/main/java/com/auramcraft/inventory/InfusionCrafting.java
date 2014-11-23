package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.tileentity.TileAuramcraft;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InfusionCrafting extends InventoryCrafting implements IAuraUser {
	private AuraContainer auraContainer;
	
	public InfusionCrafting(Container container, int width, int height, int maxAura, int tier) {
		super(container, width, height);
		auraContainer = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	@Override
	public AuraContainer getAuraContainer() {
		return auraContainer;
	}
	
	@Override
	public void setAuraContainer(AuraContainer auraContainer) {
		this.auraContainer = auraContainer;
	}
}
