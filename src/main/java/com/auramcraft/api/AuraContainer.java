package com.auramcraft.api;

import com.auramcraft.reference.Tiers;

import java.util.ArrayList;
import java.util.List;

public class AuraContainer {
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
	
	/**
	 * Tries to store the specified amount of the specified aura
	 *
	 * @return Amount that wasn't stored. -1 if the container is full to start with.
	 */
	public int store(Auras aura, int amount) {
		if(!canStoreMore())
			return -1;
		
		for(int i = 0; i < amount; i++) {
			if(!canStoreMore())
				return amount - i;
			
			auras.get(aura.getTier() - 1).set(aura.getId(), auras.get(aura.getTier() - 1).get(aura.getId()) + 1);
		}
		
		return 0;
	}
	
	/**
	 * Tries to store the auras in the amounts specified
	 */
	public void store(Object... auraList) {
		for(int i = 0; i < auraList.length; i = i + 2)
			store((Auras) auraList[i], (Integer) auraList[i+1]);
	}
	
	/**
	 * Tries to take out the specified amount of the specified aura
	 *
	 * @return If removal was successful
	 */
	public boolean remove(Auras aura, int amount) {
		if(getStoredAura(aura) < amount)
			return false;
		
		auras.get(aura.getTier()-1).set(aura.getId(), auras.get(aura.getTier()-1).get(aura.getId()) - amount);
		
		return true;
	}
	
	/**
	 * Tries to remove the auras in the amounts specified
	 */
	public void remove(Object... auraList) {
		for(int i = 0; i < auraList.length; i = i + 2)
			remove((Auras) auraList[i], (Integer) auraList[i +1]);
	}
	
	/**
	 * Transfer specified aura and amount from specified container into this container
	 *
	 * @param container Container to transfer from
	 * @param aura Aura to transfer
	 * @param amount Amount to transfer
	 */
	public void transfer(AuraContainer container, Auras aura, int amount) {
		store(aura, amount);
		container.remove(aura, amount);
	}
	
	/**
	 * @return If the container is drainable
	 */
	public boolean isDrainable() {
		return isDrainable;
	}
	
	/**
	 * @return If the container is fillable
	 */
	public boolean isFillable() {
		return isFillable;
	}
	
	/**
	 * Sets if the container is drainable
	 */
	public void setDrainable(boolean isDrainable) {
		this.isDrainable = isDrainable;
	}
	
	/**
	 * Sets if the container is fillable
	 */
	public void setFillable(boolean isFillable) {
		this.isFillable = isFillable;
	}
	
	/**
	 * @return How much aura can be contained
	 */
	public int getMaxAura() {
		return maxAura;
	}
	
	/**
	 * @return If the specified tier can be stored
	 */
	public boolean canStoreTier(int tier) {
		return tier <= this.tier;
	}
	
	/**
	 * @return If the specified aura can be stored
	 */
	public boolean canStoreAura(Auras aura) {
		return canStoreTier(aura.getTier()) && canStoreMore() && allowedAuras.isEmpty() || allowedAuras.contains(aura);
	}
	
	/**
	 * @return If the specified aura and amount can be stored
	 */
	public boolean canStoreAura(Auras aura, int amount) {
		return canStoreAura(aura) && getOpenSlots() >= amount;
	}
	
	/**
	 * @return If there is space for more aura
	 */
	public boolean canStoreMore() {
		return maxAura > getTotalStoredAura();
	}
	
	/**
	 * Adds the specified aura to the whitelist
	 */
	public void addAllowed(Auras aura) {
		if(allowedAuras.contains(aura))
			return;
		
		allowedAuras.add(aura);
	}
	
	/**
	 * Removes the specified aura from the whitelist
	 */
	public void removeAllowed(Auras aura) {
		allowedAuras.remove(aura);
	}
	
	/**
	 * Clears the whitelist
	 */
	public void clearAllowed() {
		allowedAuras.clear();
	}
	
	/**
	 * @return Allowed Auras
	 */
	public Auras[] getAllowed() {
		return allowedAuras.toArray(new Auras[allowedAuras.size()]);
	}
	
	/**
	 * @return If the specified Aura is allowed
	 */
	public boolean isAllowed(Auras aura) {
		return allowedAuras.contains(aura) || allowedAuras.isEmpty();
	}
	
	/**
	 * @return If the specified aura is contained
	 */
	public boolean containsAura(Auras aura) {
		return getStoredAura(aura) > 0;
	}
	
	/**
	 * @return If the specified auras are contained
	 */
	public boolean containsAura(List auras) {
		int numContained = 0;

		for(Object aura : auras)
			numContained += containsAura((Auras) aura) ? 1 : 0;
		
		return numContained == auras.size();
	}
	
	/**
	 * @return If the specified aura is contained in the specified amount or greater
	 */
	public boolean containsAmount(Auras aura, int amount) {
		return containsAura(aura) && getStoredAura(aura) >= amount;
	}
	
	/**
	 * @return If the specified auras are contained in the specified amounts or greater
	 */
	public boolean containsAmount(List auras, List amount) {
		int numContained = 0;
		
		for(int i = 0; i < auras.size(); i++)
			numContained += containsAmount((Auras) auras.get(i), (Integer) amount.get(i)) ? 1 : 0;
		
		return numContained == auras.size();
	}
	
	/**
	 * @param combined - List of alternating Auras and Integer objects starting with Auras
	 * @return If the specified auras are contained in the specified amounts or greater
	 */
	public boolean containsAmount(List combined) {
		int numContained = 0;
		
		for(int i = 0; i < combined.size(); i += 2)
			numContained += containsAmount((Auras) combined.get(i), (Integer) combined.get(i+1)) ? 1 : 0;
		
		return numContained == combined.size()/2;
	}
	
	/**
	 * @return The amount stored of the specified aura
	 */
	public int getStoredAura(Auras aura) {
		return auras.get(aura.getTier()-1).get(aura.getId());
	}
	
	/**
	 * @return The total stored aura
	 */
	public int getTotalStoredAura() {
		int total = 0;
		for(ArrayList<Integer> aura : auras) {
			for(Integer anAura : aura) {
				total += anAura;
			}
		}
		return total;
	}
	
	/**
	 * @return Open space for new aura
	 */
	public int getOpenSlots() {
		return maxAura - getTotalStoredAura();
	}
	
	/**
	 * @return Top tier that can be stored
	 */
	public int getTier() {
		return tier;
	}
}
