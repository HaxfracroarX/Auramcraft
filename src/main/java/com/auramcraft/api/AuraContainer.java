package com.auramcraft.api;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
import scala.util.control.Exception;
import net.minecraft.block.material.Material;
import com.auramcraft.item.Auras;
import com.auramcraft.reference.Tiers;

public class AuraContainer implements IAuraContainer {
	private int maxAura, tier;
	private ArrayList<ArrayList<Integer>> auras;
	private ArrayList<Auras> allowedAuras;
	
	public AuraContainer(int maxAura, int tier) {
		this.maxAura = maxAura;
		this.tier = tier;
		auras = new ArrayList<ArrayList<Integer>>();
		allowedAuras = new ArrayList<Auras>();
		
		for(int i = 0; i < auras.size(); i++) {
			auras.set(i, new ArrayList<Integer>(Tiers.getTotalAuras(i)));
		}
	}
	
	@Override
	public void store(Auras aura, int amount) {
		if(!canStoreAura(aura, amount))
			return;
		
		auras.get(aura.getTier()).set(aura.getId(), auras.get(aura.getTier()).get(aura.getId()) + amount);
	}
	
	@Override
	public int getMaxAura() {
		return maxAura;
	}
	
	@Override
	public boolean canStoreTier(int tier) {
		return tier <= this.tier;
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		if(allowedAuras.isEmpty() || Collections.binarySearch(allowedAuras, aura) > 0)
			return true;
		return false;
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		if(!canStoreAura(aura))
			return false;
		
		return canStoreMore() && getOpenSlots(aura) >= amount;
	}
	
	@Override
	public boolean canStoreMore() {
		return maxAura > getTotalStoredAura();
	}
	
	@Override
	public boolean containsAura(Auras aura) {
		return getStoredAura(aura) > 0 ? true : false;
	}
	
	@Override
	public int getStoredAura(Auras aura) {
		return auras.get(aura.getTier()-1).get(aura.getId());
	}
	
	@Override
	public int getTotalStoredAura() {
		int total = 0;
		for(int i = 0; i < auras.size(); i++) {
			for(int j = 0; j < auras.get(i).size(); j++) {
				total += auras.get(i).get(j);
			}
		}
		return total;
	}
	
	@Override
	public int getOpenSlots() {
		return maxAura - getTotalStoredAura();
	}
	
	@Override
	public int getOpenSlots(Auras aura) {
		return maxAura - auras.get(aura.getTier()-1).get(aura.getId());
	}
	
	@Override
	public AuraContainer getAuraContainer() {
		return this;
	}
	
	@Override
	public void setAuraContainer(AuraContainer container) {}
}
