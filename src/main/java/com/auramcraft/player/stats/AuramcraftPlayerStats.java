package com.auramcraft.player.stats;

import com.auramcraft.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class AuramcraftPlayerStats implements IExtendedEntityProperties {
	public boolean book;
	
	public AuramcraftPlayerStats() {
		book = false;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(Reference.MODID, new AuramcraftPlayerStats());
	}
	
	public static final AuramcraftPlayerStats get(EntityPlayer player) {
		return (AuramcraftPlayerStats) player.getExtendedProperties(Reference.MODID);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("bookOfAura", book);
		compound.setTag(Reference.MODID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(Reference.MODID);
		book = nbt.getBoolean("bookOfAura");
	}
	
	@Override
	public void init(Entity entity, World world) {
		
	}
}
