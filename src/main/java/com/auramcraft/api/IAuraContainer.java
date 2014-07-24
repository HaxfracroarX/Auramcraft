package com.auramcraft.api;

import com.auramcraft.item.Auras;

public interface IAuraContainer {
	/**
	 * Tries to store the specified amount of the specified aura
	 * 
	 * @return If storage was successful
	 */
	public boolean store(Auras aura, int amount);
	
	/**
	 * Tries to take out the specified amount of the specified aura
	 * 
	 * @return If removal was successful
	 */
	public boolean remove(Auras aura, int amount);
	
	/**
	 * @return If the container is drainable
	 */
	public boolean isDrainable();
	
	/**
	 * @return If the container is fillable
	 */
	public boolean isFillable();
	
	/**
	 * @return If the container is editable (isDrainable || isFillable)
	 */
	public boolean isEditable();
	
	/**
	 * Sets if the container is drainable
	 */
	public void setDrainable(boolean isDrainable);
	
	/**
	 * Sets if the container is fillable
	 */
	public void setFillable(boolean isFillable);
	
	/**
	 * @return How much aura can be contained
	 */
	public int getMaxAura();
	
	/**
	 * @return If the specified tier can be stored
	 */
	public boolean canStoreTier(int tier);
	
	/**
	 * @return If the specified aura can be stored
	 */
	public boolean canStoreAura(Auras aura);
	
	/**
	 * @return If the specified aura and amount can be stored
	 */
	public boolean canStoreAura(Auras aura, int amount);
	
	/**
	 * @return If there is space for more aura
	 */
	public boolean canStoreMore();
	
	/**
	 * Adds the specified aura to the whitelist
	 */
	public void addAllowed(Auras aura);
	
	/**
	 * Removes the specified aura from the whitelist
	 */
	public void removeAllowed(Auras aura);
	
	/**
	 * Clears the whitelist
	 */
	public void clearAllowed();
	
	/**
	 * @return Allowed Auras
	 */
	public Auras[] getAllowed();
	
	/**
	 * @return If the specified Aura is allowed
	 */
	public boolean isAllowed(Auras aura);
	
	/**
	 * @return If the specified aura is contained
	 */
	public boolean containsAura(Auras aura);
	
	/**
	 * @return The amount stored of the specified aura
	 */
	public int getStoredAura(Auras aura);
	
	/**
	 * @return The total stored aura
	 */
	public int getTotalStoredAura();
	
	/**
	 * @return Open space for new aura
	 */
	public int getOpenSlots();
	
	/**
	 * @return Top tier that can be stored
	 */
	public int getTier();
}
