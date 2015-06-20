package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.reference.Tiers;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

public class TileAuramcraftAuraUserInventory extends TileAuramcraftInventory implements IAuraUser {
	private AuraContainer container;
	
	public TileAuramcraftAuraUserInventory(String name, Block block, int slots, AuraContainer container) {
		super(name, block, slots);
		this.container = container;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		// List of Auras
		Auras[] auras = Auras.values();
		
		// Get aura values
		for(int i = 0, rep = 0; i < Tiers.getTotalTiers(); i++) {
			for(int j = 0; j < Tiers.getTotalAuras(i); j++, rep++) {
				container.store(auras[rep], nbtTagCompound.getInteger(auras[rep].toString()));
				
				// Add allowed
				if(nbtTagCompound.getBoolean(auras[rep].toString()+" isAllowed"))
					container.addAllowed(auras[rep]);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// List of Auras
		Auras[] auras = Auras.values();
		
		// Add Aura info
		for(Auras aura : auras) {
			// How much aura there is
			nbtTagCompound.setInteger(aura.toString(), container.getStoredAura(aura));

			// If we are allowed to store the aura
			nbtTagCompound.setBoolean(aura.toString() + " isAllowed", container.isAllowed(aura));
		}
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
