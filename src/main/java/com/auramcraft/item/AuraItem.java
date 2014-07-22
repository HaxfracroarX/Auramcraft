package com.auramcraft.item;

import java.util.List;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.reference.Tiers;
import com.auramcraft.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AuraItem extends Item implements IAuraContainer {
	private AuraContainer container;
	
	public AuraItem(int maxAura, int tier) {
		container = new AuraContainer(maxAura, tier);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
		int offset = 0;
		
		for(int i = 0; i < getTier(); i++) {
			list.add("Tier " + (i+1) + ":");
			
			for(int j = 0; j < Tiers.getTotalAuras(i); j++, offset++) {
				Auras[] auras = Auras.values();
				
				if(getStoredAura(auras[offset]) > 0)
					list.add("  " + auras[offset].toString() + ": " + getStoredAura(auras[offset]));
			}
		}
	}
	
	@Override
	public boolean store(Auras aura, int amount) {
		return container.store(aura, amount);
	}
	
	@Override
	public boolean remove(Auras aura, int amount) {
		return container.remove(aura, amount);
	}
	
	@Override
	public int getMaxAura() {
		return container.getMaxAura();
	}
	
	@Override
	public boolean canStoreTier(int tier) {
		return container.canStoreTier(tier);
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		return container.canStoreAura(aura);
	}
	
	@Override
	public boolean canStoreAura(Auras aura, int amount) {
		return container.canStoreAura(aura, amount);
	}
	
	@Override
	public boolean canStoreMore() {
		return container.canStoreMore();
	}
	
	@Override
	public boolean containsAura(Auras aura) {
		return container.containsAura(aura);
	}
	
	@Override
	public int getStoredAura(Auras aura) {
		return container.getStoredAura(aura);
	}
	
	@Override
	public int getTotalStoredAura() {
		return container.getTotalStoredAura();
	}
	
	@Override
	public int getOpenSlots() {
		return container.getOpenSlots();
	}
	
	@Override
	public int getTier() {
		return container.getTier();
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
