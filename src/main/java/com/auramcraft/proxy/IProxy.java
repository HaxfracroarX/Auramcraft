package com.auramcraft.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IProxy {
	void registerTileEntities();
	
	void registerRenderers();
	
	void registerEventHandlers();
	
	void registerGUIHandlers();
	
	void storeEntityData(EntityPlayer player, NBTTagCompound compound);
	
	NBTTagCompound getEntityData(EntityPlayer player);
}
