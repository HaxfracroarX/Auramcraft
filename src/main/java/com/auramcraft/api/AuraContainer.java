package com.auramcraft.api;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
import scala.util.control.Exception;
import net.minecraft.block.material.Material;
import com.auramcraft.item.Auras;
import com.auramcraft.reference.Tiers;
import com.auramcraft.util.LogHelper;

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
	public boolean store(Auras aura, int amount) {
		if(!canStoreAura(aura, amount))
			return false;
		
		auras.get(aura.getTier()-1).set(aura.getId(), auras.get(aura.getTier()-1).get(aura.getId()) + amount);
		
		return true;
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
		return allowedAuras.isEmpty() || Collections.binarySearch(allowedAuras, aura) > 0;
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		if(!canStoreAura(aura))
			return false;
		
		return canStoreMore() && getOpenSlots() >= amount;
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
		return (Auras[]) allowedAuras.toArray();
	}
	
	@Override
	public boolean isAllowed(Auras aura) {
		return allowedAuras.contains(aura) || allowedAuras.isEmpty();
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
	public int getTier() {
		return tier;
	}
}
