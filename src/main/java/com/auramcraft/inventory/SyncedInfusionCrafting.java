package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.Auras;
import com.auramcraft.tileentity.TileEntityAuramcraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class SyncedInfusionCrafting extends InventoryCrafting implements IAuraContainer {
	private AuraContainer auraContainer;
	private TileEntityAuramcraft entity;
	private int start;
	
	public SyncedInfusionCrafting(Container container, int width, int height, int maxAura, int tier, TileEntityAuramcraft entity, int start) {
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
	public boolean store(Auras aura, int amount) {
		return auraContainer.store(aura, amount);
	}
	
	@Override
	public boolean remove(Auras aura, int amount) {
		return auraContainer.remove(aura, amount);
	}
	
	@Override
	public int getMaxAura() {
		return auraContainer.getMaxAura();
	}
	
	@Override
	public boolean canStoreTier(int tier) {
		return auraContainer.canStoreTier(tier);
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		return auraContainer.canStoreAura(aura);
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		return auraContainer.canStoreAura(aura, amount);
	}
	
	@Override
	public boolean canStoreMore() {
		return auraContainer.canStoreMore();
	}
	
	@Override
	public boolean containsAura(Auras aura) {
		return auraContainer.containsAura(aura);
	}
	
	@Override
	public int getStoredAura(Auras aura) {
		return auraContainer.getStoredAura(aura);
	}
	
	@Override
	public int getTotalStoredAura() {
		return auraContainer.getTotalStoredAura();
	}
	
	@Override
	public int getOpenSlots() {
		return auraContainer.getOpenSlots();
	}
	
	@Override
	public int getTier() {
		return auraContainer.getTier();
	}
	
	@Override
	public AuraContainer getAuraContainer() {
		return auraContainer;
	}
	
	public void setAuraContainer(AuraContainer auraContainer) {
		this.auraContainer = auraContainer;
	}
}
