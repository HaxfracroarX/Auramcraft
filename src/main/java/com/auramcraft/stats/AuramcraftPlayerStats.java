package com.auramcraft.stats;

import com.auramcraft.reference.PageIds;
import com.auramcraft.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class AuramcraftPlayerStats implements IExtendedEntityProperties {
	private boolean book;
	private byte[] pages = new byte[PageIds.BOOKLENGTH];
	
	private AuramcraftPlayerStats() {
		book = false;
		for(int i = 0; i < pages.length; i++) {
			pages[i] = 0;
		}
	}
	
	public static void register(EntityPlayer player) {
		player.registerExtendedProperties(Reference.MODID, new AuramcraftPlayerStats());
	}
	
	public static AuramcraftPlayerStats get(EntityPlayer player) {
		return (AuramcraftPlayerStats) player.getExtendedProperties(Reference.MODID);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("bookOfAura", gotBook());
		nbt.setByteArray("ResearchedPages", pages);
		compound.setTag(Reference.MODID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(Reference.MODID);
		setBook(nbt.getBoolean("bookOfAura"));
		pages = nbt.getByteArray("ResearchedPages");
	}
	
	@Override
	public void init(Entity entity, World world) {
		
	}
	
	private boolean[] getBoolFromByte(byte[] bytes) {
		boolean[] bools = new boolean[bytes.length];
		for(int i = 0; i < bytes.length; i++)
			bools[i] = bytes[i] == 1;
		return bools;
	}
	
	private byte[] getByteFromBool(boolean[] bools) {
		byte[] bytes = new byte[bools.length];
		for(int i = 0; i < bools.length; i++)
			bytes[i] = (byte) (bools[i] ? 1 : 0);
		return bytes;
	}
	
	/**
	 * @return if the player has gotten the Book of Aura
	 */
	public boolean gotBook() {
		return book;
	}
	
	/**
	 * @param book if the player has gotten the Book of Aura
	 */
	public void setBook(boolean book) {
		this.book = book;
	}
	
	/**
	 * @return the pages researched by the player
	 */
	public boolean[] getPages() {
		return getBoolFromByte(pages);
	}
	
	/**
	 * @param pages researched by the player
	 */
	public void setPages(boolean[] pages) {
		this.pages = getByteFromBool(pages);
	}
}
