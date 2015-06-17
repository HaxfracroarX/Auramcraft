package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.reference.Tiers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "SameParameterValue", "WeakerAccess"})
public class AuraItem extends Item implements IAuraUser {
	private AuraContainer container;
	
	public AuraItem(int maxAura, int tier) {
		container = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		initNBT(itemStack);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
		// List of all Auras
		Auras[] auras = Auras.values();
		
		// Container of itemStack
		AuraContainer container = getAuraContainer(itemStack);
		
		// Current aura
		int auraID = 0;
		
		// Loop through every tier we can store
		for(int i = 0; i < container.getTier(); i++) {
			boolean isEmpty = true;
			
			// Check if there is any aura in this tier
			for(int j = 0; j < Tiers.getTotalAuras(i); j++) {
				if(container.getStoredAura(auras[auraID + j]) > 0) {
					isEmpty = false;
					break;
				}
			}
			
			// If there is no aura from this tier, skip adding the empty tier
			if(isEmpty) {
				list.add("Tier " + (i+1) + ": Empty");
				auraID += Tiers.getTotalAuras(i);
				continue;
			}
			
			list.add("Tier " + (i+1) + ":");
			
			// Loop through every aura in the tier
			for(int j = 0; j < Tiers.getTotalAuras(i); j++)
				list.add("  " + auras[auraID + j].toString() + ": " + container.getStoredAura(auras[auraID + j]));
			
			auraID += Tiers.getTotalAuras(i);
		}
	}
	
	public static void updateNBT(ItemStack itemStack, AuraContainer container) {
		// Give it a new tag
		itemStack.stackTagCompound = new NBTTagCompound();
		
		// List of all Auras
		Auras[] auras = Auras.values();
		
		// Add Aura info
		for(Auras aura : auras) {
			// How much aura there is
			itemStack.stackTagCompound.setInteger(aura.toString(), container.getStoredAura(aura));
			// If we are allowed to store the aura
			itemStack.stackTagCompound.setBoolean(aura.toString() + " isAllowed", container.isAllowed(aura));
		}
		
		// Max Aura
		itemStack.stackTagCompound.setInteger("Max Aura", container.getMaxAura());
		// Tier
		itemStack.stackTagCompound.setInteger("Tier", container.getTier());
		// isDrainable
		itemStack.stackTagCompound.setBoolean("isDrainable", container.isDrainable());
		// isFillable
		itemStack.stackTagCompound.setBoolean("isFillable", container.isFillable());
	}
	
	public static AuraContainer getAuraContainer(ItemStack itemStack) {
		// If NBT hasn't been initialized yet
		initNBT(itemStack);
		
		// Get values
		int maxAura = itemStack.stackTagCompound.getInteger("Max Aura");
		int tier = itemStack.stackTagCompound.getInteger("Tier");
		boolean isDrainable = itemStack.stackTagCompound.getBoolean("isDrainable");
		boolean isFillable = itemStack.stackTagCompound.getBoolean("isFillable");
		
		// Make variables
		ArrayList<ArrayList<Integer>> auras = new ArrayList<ArrayList<Integer>>();
		ArrayList<Auras> allowedAuras = new ArrayList<Auras>();
		Auras[] auraList = Auras.values();
		
		// Get aura values
		for(int i = 0, rep = 0; i < Tiers.getTotalTiers(); i++) {
			// Init arrayLists
			auras.add(new ArrayList<Integer>());
			
			for(int j = 0; j < Tiers.getTotalAuras(i); j++, rep++) {
				// Add aura values
				auras.get(i).add(itemStack.stackTagCompound.getInteger(auraList[rep].toString()));
				
				// Add allowed
				if(itemStack.stackTagCompound.getBoolean(auraList[rep].toString()+" isAllowed"))
					allowedAuras.add(auraList[rep]);
			}
		}
		
		if(allowedAuras.size() == auraList.length)
			allowedAuras.clear();
		
		return new AuraContainer(maxAura, tier, auras, allowedAuras, isDrainable, isFillable);
	}
	
	public static void initNBT(ItemStack itemStack) {
		if(itemStack.stackTagCompound == null)
			updateNBT(itemStack, ((AuraItem) itemStack.getItem()).getAuraContainer());
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
