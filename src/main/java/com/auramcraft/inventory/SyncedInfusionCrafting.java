package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.tileentity.TileEntityAuramcraft;
import com.auramcraft.tileentity.TileEntityAuramcraftInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class SyncedInfusionCrafting extends InventoryCrafting implements IAuraUser {
	private AuraContainer auraContainer;
	private TileEntityAuramcraftInventory entity;
	private int start;
	
	public SyncedInfusionCrafting(Container container, int width, int height, int maxAura, int tier, TileEntityAuramcraftInventory entity, int start) {
		super(container, width, height);
		auraContainer = new AuraContainer(maxAura, tier);
		this.entity = entity;
		this.start = start;
	}
	
	@Override
	public void openInventory() {
		for(int i = start; i < 9 + start; i++)
			setInventorySlotContents(i, entity.getStackInSlot(i));
	}
	
	@Override
    public void closeInventory() {
		for(int i = start; i < 9 + start; i++)
			entity.setInventorySlotContents(i, getStackInSlot(i));
	}
	
	@Override
	public AuraContainer getAuraContainer() {
		return auraContainer;
	}
	
	public void setAuraContainer(AuraContainer auraContainer) {
		this.auraContainer = auraContainer;
	}
}
