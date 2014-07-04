package com.auramcraft.item;

import java.util.ArrayList;
import com.auramcraft.reference.Tiers;
import net.minecraft.item.Item;

public abstract class IAuraContainer extends Item {
	private int maxAura, tier;
	private ArrayList<ArrayList<Integer>> auras = new ArrayList<ArrayList<Integer>>();
	
	public IAuraContainer(int maxAura, int tier) {
		this.maxAura = maxAura;
		this.tier = tier;
		
		for(int i = 0; i < auras.size(); i++) {
			auras.set(i, new ArrayList<Integer>(Tiers.getTotalAuras(i)));
		}
	}
	
	public void store(Auras aura, int amount) {
		if(!canStoreAura(aura, amount))
			return;
		
		auras.get(aura.getTier()).set(aura.getId(), auras.get(aura.getTier()).get(aura.getId()) + amount);
	}
	
	public int getMaxAura() {
		return maxAura;
	}
	
	public boolean canStoreTier(int tier) {
		return tier <= this.tier;
	}
	
	public abstract boolean canStoreAura(Auras aura);
	
	public boolean canStoreAura(Auras aura, int amount) {
		if(!canStoreAura(aura))
			return false;
		
		return canStoreMore() && getOpenSlots(aura) >= amount;
	}
	
	public boolean canStoreMore() {
		return maxAura > getTotalStoredAura();
	}
	
	public int getTotalStoredAura() {
		int total = 0;
		for(int i = 0; i < auras.size(); i++) {
			for(int j = 0; j < auras.get(i).size(); j++) {
				total += auras.get(i).get(j);
			}
		}
		return total;
	}
	
	public int getOpenSlots() {
		return maxAura - getTotalStoredAura();
	}
	
	public int getOpenSlots(Auras aura) {
		return maxAura - auras.get(aura.getTier()).get(aura.getId());
	}
}
