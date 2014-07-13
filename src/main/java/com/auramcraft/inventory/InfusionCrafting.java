package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.Auras;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class InfusionCrafting extends InventoryCrafting implements IAuraContainer {
	AuraContainer auraContainer;
	
	public InfusionCrafting(Container container, int width, int height, int maxAura, int tier) {
		super(container, width, height);
		auraContainer = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public void store(Auras aura, int amount) {
		auraContainer.store(aura, amount);
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
	public int getOpenSlots(Auras aura) {
		return auraContainer.getOpenSlots(aura);
	}

	@Override
	public AuraContainer getAuraContainer() {
		return auraContainer;
	}
	
	public void setAuraContainer(AuraContainer auraContainer) {
		this.auraContainer = auraContainer;
	}
}
