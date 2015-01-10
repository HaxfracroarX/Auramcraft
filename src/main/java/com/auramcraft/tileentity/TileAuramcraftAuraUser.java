package com.auramcraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;

public class TileAuramcraftAuraUser extends TileAuramcraft implements IAuraUser {
	private AuraContainer container;
	
	public TileAuramcraftAuraUser(String name, Block block, AuraContainer container) {
		super(name, block);
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
		for(int i = 0; i < auras.length; i++) {
			// How much aura there is
			nbtTagCompound.setInteger(auras[i].toString(), container.getStoredAura(auras[i]));
			
			// If we are allowed to store the aura
			nbtTagCompound.setBoolean(auras[i].toString()+" isAllowed", container.isAllowed(auras[i]));
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
