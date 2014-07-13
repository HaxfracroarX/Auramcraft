package com.auramcraft.api;

import com.auramcraft.item.Auras;

public interface IAuraContainer {
	public void store(Auras aura, int amount);
	
	public int getMaxAura();
	
	public boolean canStoreTier(int tier);
	
	public boolean canStoreAura(Auras aura);
	
	public boolean canStoreAura(Auras aura, int amount);
	
	public boolean canStoreMore();
	
	public boolean containsAura(Auras aura);
	
	public int getStoredAura(Auras aura);
	
	public int getTotalStoredAura();
	
	public int getOpenSlots();
	
	public int getOpenSlots(Auras aura);
	
	public AuraContainer getAuraContainer();
	
	public void setAuraContainer(AuraContainer container);
}
