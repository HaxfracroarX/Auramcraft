package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.reference.Tiers;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

@SuppressWarnings("WeakerAccess")
public class TileAuraUser extends TileAuramcraft implements IAuraUser {
	private AuraContainer container;
	
	public TileAuraUser(String name, Block block, AuraContainer container) {
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
				
				String key = auras[rep].toString()+" isAllowed";
				
				// Add allowed
				if(nbtTagCompound.hasKey(key) && nbtTagCompound.getBoolean(key))
					container.addAllowed(auras[rep]);
			}
		}
		
		if(nbtTagCompound.getString("Mode").equals("All"))
			container.clearAllowed();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// List of Auras
		Auras[] auras = Auras.values();
		
		// Add list mode
		if(container.getAllowed().length == 0)
			nbtTagCompound.setString("Mode", "All");
		else
			nbtTagCompound.setString("Mode", "Whitelist");
		
		// Add Aura info
		for(Auras aura : auras) {
			// How much aura there is
			nbtTagCompound.setInteger(aura.toString(), container.getStoredAura(aura));
			
			// If we are allowed to store the aura
			if(container.getAllowed().length != 0 && container.isAllowed(aura))
				nbtTagCompound.setBoolean(aura.toString() + " isAllowed", true);
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
