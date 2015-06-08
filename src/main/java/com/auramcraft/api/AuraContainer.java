package com.auramcraft.api;

import com.auramcraft.reference.Tiers;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CanBeFinal")
public class AuraContainer implements IAuraContainer {
	private int maxAura, tier;
	private ArrayList<ArrayList<Integer>> auras;
	private ArrayList<Auras> allowedAuras;
	private boolean isDrainable, isFillable;
	
	public AuraContainer(int maxAura, int tier) {
		this.maxAura = maxAura;
		this.tier = tier;
		auras = new ArrayList<ArrayList<Integer>>();
		allowedAuras = new ArrayList<Auras>();
		isDrainable = true;
		isFillable = true;
		
		for(int i = 0; i < Tiers.getTotalTiers(); i++) {
			auras.add(new ArrayList<Integer>());
			
			for(int j = 0; j < Tiers.getTotalAuras(i); j++)
				auras.get(i).add(0);
		}
	}
	
	public AuraContainer(int maxAura, int tier, ArrayList<ArrayList<Integer>> auras, ArrayList<Auras> allowedAuras, boolean isDrainable, boolean isFillable) {
		this.maxAura = maxAura;
		this.tier = tier;
		this.auras = auras;
		this.allowedAuras = allowedAuras;
		this.isDrainable = isDrainable;
		this.isFillable = isFillable;
	}
	
	@Override
	public int store(Auras aura, int amount) {
		if(!canStoreAura(aura, amount))
			return -1;
		
		for(int i = 0; i < amount; i++) {
			if(!canStoreMore())
				return amount - i;
			
			auras.get(aura.getTier() - 1).set(aura.getId(), auras.get(aura.getTier() - 1).get(aura.getId()) + 1);
		}
		
		return 0;
	}
	
	@Override
	public void store(Object... auraList) {
		for(int i = 0; i < auraList.length; i = i + 2)
			store((Auras) auraList[i], (Integer) auraList[i+1]);
	}
	
	@Override
	public boolean remove(Auras aura, int amount) {
		if(getStoredAura(aura) < amount)
			return false;
		
		auras.get(aura.getTier()-1).set(aura.getId(), auras.get(aura.getTier()-1).get(aura.getId()) - amount);
		
		return true;
	}
	
	@Override
	public void remove(Object... auraList) {
		for(int i = 0; i < auraList.length; i = i + 2)
			remove((Auras) auraList[i], (Integer) auraList[i +1]);
	}
	
	@Override
	public void transfer(AuraContainer container, Auras aura, int amount) {
		store(aura, amount);
		container.remove(aura, amount);
	}
	
	@Override
	public boolean isDrainable() {
		return isDrainable;
	}
	
	@Override
	public boolean isFillable() {
		return isFillable;
	}
	
	@Override
	public boolean isEditable() {
		return isDrainable() || isFillable();
	}
	
	@Override
	public void setDrainable(boolean isDrainable) {
		this.isDrainable = isDrainable;
	}
	
	@Override
	public void setFillable(boolean isFillable) {
		this.isFillable = isFillable;
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
		return allowedAuras.isEmpty() || allowedAuras.contains(aura);
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		return canStoreAura(aura) && canStoreMore();

	}
	
	@Override
	public boolean canStoreMore() {
		return maxAura > getTotalStoredAura();
	}
	
	@Override
	public void addAllowed(Auras aura) {
		if(allowedAuras.contains(aura))
			return;
		
		allowedAuras.add(aura);
	}
	
	@Override
	public void removeAllowed(Auras aura) {
		allowedAuras.remove(aura);
	}
	
	@Override
	public void clearAllowed() {
		allowedAuras.clear();
	}
	
	@Override
	public Auras[] getAllowed() {
		return allowedAuras.toArray(new Auras[allowedAuras.size()]);
	}
	
	@Override
	public boolean isAllowed(Auras aura) {
		return allowedAuras.contains(aura) || allowedAuras.isEmpty();
	}
	
	@Override
	public boolean containsAura(Auras aura) {
		return getStoredAura(aura) > 0;
	}
	
	@Override
	public boolean containsAura(List auras) {
		int numContained = 0;

		for(Object aura : auras)
			numContained += containsAura((Auras) aura) ? 1 : 0;
		
		return numContained == auras.size();
	}
	
	@Override
	public boolean containsAmount(Auras aura, int amount) {
		return containsAura(aura) && getStoredAura(aura) >= amount;
	}
	
	@Override
	public boolean containsAmount(List auras, List amount) {
		int numContained = 0;
		
		for(int i = 0; i < auras.size(); i++)
			numContained += containsAmount((Auras) auras.get(i), (Integer) amount.get(i)) ? 1 : 0;
		
		return numContained == auras.size();
	}
	
	@Override
	public boolean containsAmount(List combined) {
		int numContained = 0;
		
		for(int i = 0; i < combined.size(); i += 2)
			numContained += containsAmount((Auras) combined.get(i), (Integer) combined.get(i+1)) ? 1 : 0;
		
		return numContained == combined.size()/2;
	}
	
	@Override
	public int getStoredAura(Auras aura) {
		return auras.get(aura.getTier()-1).get(aura.getId());
	}
	
	@Override
	public int getTotalStoredAura() {
		int total = 0;
		for(ArrayList<Integer> aura : auras) {
			for(Integer anAura : aura) {
				total += anAura;
			}
		}
		return total;
	}
	
	@Override
	public int getOpenSlots() {
		return maxAura - getTotalStoredAura();
	}
	
	@Override
	public int getTier() {
		return tier;
	}
}
