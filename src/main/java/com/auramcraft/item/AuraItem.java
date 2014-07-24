package com.auramcraft.item;

import java.util.ArrayList;
import java.util.List;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.reference.Tiers;
import com.auramcraft.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AuraItem extends Item implements IAuraUser {
	private AuraContainer container;
	
	public AuraItem(int maxAura, int tier) {
		container = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
		// Current id of aura
		int offset = 0;
		
		// List of all Auras
		Auras[] auras = Auras.values();
		
		// Container of itemstack
		AuraContainer container = getAuraContainer(itemStack);
		
		// Loop through every tier we can store
		for(int i = 0; i < container.getTier(); i++) {
			list.add("Tier " + (i+1) + ":");
			
			// Loop through every aura in the tier
			for(int j = 0; j < Tiers.getTotalAuras(i); j++, offset++) {
				// If we have more than 1 of an aura, show it
				if(container.getStoredAura(auras[offset]) > 0)
					list.add("  " + auras[offset].toString() + ": " + container.getStoredAura(auras[offset]));
			}
		}
	}
	
	public static void updateNBT(ItemStack itemStack, AuraContainer container) {
		// Give it a new tag
		itemStack.stackTagCompound = new NBTTagCompound();
		
		// List of all Auras
		Auras[] auras = Auras.values();
		
		// Add Aura info
		for(int i = 0; i < auras.length; i++) {
			// How much aura there is
			itemStack.stackTagCompound.setInteger(auras[i].toString(), container.getStoredAura(auras[i]));
			// If we are allowed to store the aura
			itemStack.stackTagCompound.setBoolean(auras[i].toString()+" isAllowed", container.isAllowed(auras[i]));
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
		// If NBT hasn't been initilized yet
		if(!(itemStack.stackTagCompound != null))
			updateNBT(itemStack, ((AuraItem) itemStack.getItem()).getAuraContainer());
		
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
				allowedAuras.add(auraList[rep]);
				
				// Remove unapproved auras
				if(!itemStack.stackTagCompound.getBoolean(auraList[rep].toString()+" isAllowed"))
					allowedAuras.remove(auraList[rep]);
			}
		}
		
		if(allowedAuras.size() == auraList.length)
			allowedAuras.clear();
		
		return new AuraContainer(maxAura, tier, auras, allowedAuras, isDrainable, isFillable);
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
