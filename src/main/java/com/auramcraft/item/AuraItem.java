package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import net.minecraft.item.Item;

public class AuraItem extends Item implements IAuraContainer {
	private AuraContainer container;
	
	public AuraItem(int maxAura, int tier) {
		container = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public void store(Auras aura, int amount) {
		container.store(aura, amount);
	}
	
	@Override
	public int getMaxAura() {
		return container.getMaxAura();
	}
	
	@Override
	public boolean canStoreTier(int tier) {
		return container.canStoreTier(tier);
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		return container.canStoreAura(aura);
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		return container.canStoreAura(aura, amount);
	}
	
	@Override
	public boolean canStoreMore() {
		return container.canStoreMore();
	}
	
	@Override
	public boolean containsAura(Auras aura) {
		return container.containsAura(aura);
	}
	
	@Override
	public int getStoredAura(Auras aura) {
		return container.getStoredAura(aura);
	}
	
	@Override
	public int getTotalStoredAura() {
		return container.getTotalStoredAura();
	}
	
	@Override
	public int getOpenSlots() {
		return container.getOpenSlots();
	}

	@Override
	public AuraContainer getAuraContainer() {
		return container;
	}

	@Override
	public void setAuraContainer(AuraContainer container) {
		this.container = container;
	}
}
