package com.auramcraft.stats;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MessagePlayerStats;
import com.auramcraft.reference.BookIds;
import com.auramcraft.reference.Reference;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;

public class AuramcraftPlayerStats implements IExtendedEntityProperties, IMessageHandler<MessagePlayerStats, IMessage> {
	private boolean book;
	private boolean isAnnouncing;
	private float pageTime;
	private int regenTicks;
	private String announcment;
	private ArrayList<byte[]> pages;
	private AuraContainer container;
	
	public AuramcraftPlayerStats() {
		book = false;
		isAnnouncing = false;
		pageTime = 0;
		announcment = "";
		regenTicks = 0;
		
		pages = new ArrayList<byte[]>();
		for(int i = 0; i < BookIds.tabs; i++)
			pages.add(new byte[BookIds.getTab(i).getPages().size()]);
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
		
		// Set Book
		nbt.setBoolean("bookOfAura", gotBook());
		
		// Set Research
		for(int i = 0; i < pages.size(); i++)
			nbt.setByteArray("Tab" + i + "Research", pages.get(i));
		
		// Set Allowed Auras
		Auras[] allowedAuras = container.getAllowed();
		int[] intAllowed = new int[allowedAuras.length];
		for(int i = 0; i < allowedAuras.length; i++)
			intAllowed[i] = allowedAuras[i].getId();
		nbt.setIntArray("playerAffinity", intAllowed);
		
		// Set Max Aura
		nbt.setInteger("playerMaxAura", container.getMaxAura());
		
		// Set Stored Aura
		int[] stored = new int[5];
		for(int i = 0; i < stored.length; i++)
			stored[i] = container.getStoredAura(Auras.values()[i]);
		nbt.setIntArray("playerAuraAmount", stored);
		
		compound.setTag(Reference.MODID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(Reference.MODID);
		
		// Get Book
		book = nbt.getBoolean("bookOfAura");
		
		// Get Research
		for(int i = 0; i < pages.size(); i++)
			pages.set(i, nbt.getByteArray("Tab" + i + "Research"));
		
		// Get Max Aura
		container = new AuraContainer(nbt.getInteger("playerMaxAura"), 1);
		
		// Get Allowed Auras
		int[] intAllowed = nbt.getIntArray("playerAffinity");
		for(int allowed : intAllowed)
			container.addAllowed(Auras.values()[allowed]);
		
		// Get Stored Aura
		int[] stored = nbt.getIntArray("playerAuraAmount");
		
		for(int i = 0; i < stored.length; i++)
			container.store(Auras.values()[i], stored[i]);
	}
	
	@Override
	public IMessage onMessage(MessagePlayerStats message, MessageContext ctx) {
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(Minecraft.getMinecraft().thePlayer);
		
		stats.book = message.book;
		stats.pages = message.pages;
		
		container = new AuraContainer(message.maxAura, 1);
		for(int i = 0; i < message.allowedAuras.length; i++) {
			Auras allowed = Auras.values()[message.allowedAuras[i]];
			container.addAllowed(allowed);
			container.store(allowed, message.storedAura[i]);
		}
		stats.setAuraContainer(container);
		
		return null;
	}
	
	public void sendPacket(EntityPlayer player) {
		PacketHandler.wrapper.sendTo(new MessagePlayerStats(this), (EntityPlayerMP) player);
	}
	
	public static void update(EntityPlayer player) {
		AuramcraftPlayerStats stats = get(player);
		AuraContainer container = stats.getAuraContainer();
		Auras[] allowedAuras = container.getAllowed();
		
		// Replenish auras once every 15 seconds (300 ticks)
		if(stats.getRegenTicks() >= 300) {
			for(Auras allowed : allowedAuras)
				container.store(allowed, 1);
			
			stats.setAuraContainer(container);
			stats.setRegenTicks(0);
			stats.sendPacket(player);
		}
		else
			stats.setRegenTicks(stats.getRegenTicks()+1);
	}
	
	@Override
	public void init(Entity entity, World world) {}
	
	public boolean[] getBoolFromByte(byte[] bytes) {
		boolean[] bools = new boolean[bytes.length];
		for(int i = 0; i < bytes.length; i++)
			bools[i] = bytes[i] == 1;
		return bools;
	}
	
	public byte[] getByteFromBool(boolean[] bools) {
		byte[] bytes = new byte[bools.length];
		for(int i = 0; i < bools.length; i++)
			bytes[i] = (byte) (bools[i] ? 1 : 0);
		return bytes;
	}
	
	public int getRegenTicks() {
		return regenTicks;
	}

	public void setRegenTicks(int regenTicks) {
		this.regenTicks = regenTicks;
	}
	
	/**
	 * Set variables to count announcment lifetime
	 */
	public void initPageAnnouncment(String announcment) {
		this.announcment = "New Page: " + announcment;
		isAnnouncing = true;
		pageTime = 3f;
	}
	
	/**
	 * @return The time left in seconds
	 */
	public float getPageTime() {
		return pageTime;
	}
	
	/**
	 * @param pageTime The time left in seconds
	 */
	public void setPageTime(float pageTime) {
		this.pageTime = pageTime;
	}
	
	/**
	 * @return if an announcment is showing
	 */
	public boolean isAnnouncing() {
		return isAnnouncing;
	}
	
	/**
	 * @param isAnnouncing set if announcing
	 */
	public void setAnnouncing(boolean isAnnouncing) {
		this.isAnnouncing = isAnnouncing;
	}
	
	/**
	 * @return the announcment
	 */
	public String getAnnouncment() {
		return announcment;
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
	public boolean[] getPages(int tab) {
		return getBoolFromByte(pages.get(tab));
	}
	
	/**
	 * @param pages researched by the player
	 */
	public void setPages(int tab, boolean[] pages) {
		this.pages.set(tab, getByteFromBool(pages));
	}
	
	/**
	 * @return the AuraContainer of the player
	 */
	public AuraContainer getAuraContainer() {
		return container;
	}

	/**
	 * @param container The AuraContainer to set
	 */
	public void setAuraContainer(AuraContainer container) {
		this.container = container;
	}
}
