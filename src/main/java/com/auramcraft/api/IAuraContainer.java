package com.auramcraft.api;

import java.util.List;


@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public interface IAuraContainer {
	/**
	 * Tries to store the specified amount of the specified aura
	 * 
	 * @return If storage was successful
	 */
	boolean store(Auras aura, int amount);
	
	/**
	 * Tries to store the auras in the amounts specified
	 */
	void store(Object... auraList);
	
	/**
	 * Tries to take out the specified amount of the specified aura
	 * 
	 * @return If removal was successful
	 */
	boolean remove(Auras aura, int amount);
	
	/**
	 * Tries to remove the auras in the amounts specified
	 */
	void remove(Object... auraList);
	
	/**
	 * Transfer specified aura and amount from specified container into this container
	 * 
	 * @param container Container to transfer from
	 * @param aura Aura to transfer
	 * @param amount Amount to transfer
	 */
	void transfer(AuraContainer container, Auras aura, int amount);
	
	/**
	 * @return If the container is drainable
	 */
	boolean isDrainable();
	
	/**
	 * @return If the container is fillable
	 */
	boolean isFillable();
	
	/**
	 * @return If the container is editable (isDrainable || isFillable)
	 */
	boolean isEditable();
	
	/**
	 * Sets if the container is drainable
	 */
	void setDrainable(boolean isDrainable);
	
	/**
	 * Sets if the container is fillable
	 */
	void setFillable(boolean isFillable);
	
	/**
	 * @return How much aura can be contained
	 */
	int getMaxAura();
	
	/**
	 * @return If the specified tier can be stored
	 */
	boolean canStoreTier(int tier);
	
	/**
	 * @return If the specified aura can be stored
	 */
	boolean canStoreAura(Auras aura);
	
	/**
	 * @return If the specified aura and amount can be stored
	 */
	boolean canStoreAura(Auras aura, int amount);
	
	/**
	 * @return If there is space for more aura
	 */
	boolean canStoreMore();
	
	/**
	 * Adds the specified aura to the whitelist
	 */
	void addAllowed(Auras aura);
	
	/**
	 * Removes the specified aura from the whitelist
	 */
	void removeAllowed(Auras aura);
	
	/**
	 * Clears the whitelist
	 */
	void clearAllowed();
	
	/**
	 * @return Allowed Auras
	 */
	Auras[] getAllowed();
	
	/**
	 * @return If the specified Aura is allowed
	 */
	boolean isAllowed(Auras aura);
	
	/**
	 * @return If the specified aura is contained
	 */
	boolean containsAura(Auras aura);
	
	/**
	 * @return If the specified auras are contained
	 */
	boolean containsAura(List auras);
	
	/**
	 * @return If the specified aura is contained in the specified amount or greater
	 */
	boolean containsAmount(Auras aura, int amount);
	
	/**
	 * @return If the specified auras are contained in the specified amounts or greater
	 */
	boolean containsAmount(List auras, List amount);
	
	/**
	 * @param combined - List of alternating Auras and Integer objects starting with Auras
	 * @return If the specified auras are contained in the specified amounts or greater
	 */
	boolean containsAmount(List combined);
	
	/**
	 * @return The amount stored of the specified aura
	 */
	int getStoredAura(Auras aura);
	
	/**
	 * @return The total stored aura
	 */
	int getTotalStoredAura();
	
	/**
	 * @return Open space for new aura
	 */
	int getOpenSlots();
	
	/**
	 * @return Top tier that can be stored
	 */
	int getTier();
}
