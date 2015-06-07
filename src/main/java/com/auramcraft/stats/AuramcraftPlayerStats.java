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
	private String announcment;
	private ArrayList<byte[]> pages;
	private AuraContainer container;
	
	public AuramcraftPlayerStats() {
		book = false;
		isAnnouncing = false;
		pageTime = 0;
		announcment = "";
		
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
		nbt.setBoolean("bookOfAura", gotBook());
		
		for(int i = 0; i < pages.size(); i++)
			nbt.setByteArray("Tab" + i + "Research", pages.get(i));
		
		nbt.setInteger("playerAffinity", container.getAllowed()[0].getId());
		nbt.setInteger("playerMaxAura", container.getMaxAura());
		nbt.setInteger("playerAuraAmount", container.getStoredAura(container.getAllowed()[0]));
		compound.setTag(Reference.MODID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(Reference.MODID);
		
		book = nbt.getBoolean("bookOfAura");
		
		for(int i = 0; i < pages.size(); i++)
			pages.set(i, nbt.getByteArray("Tab" + i + "Research"));
		
		container = new AuraContainer(nbt.getInteger("playerMaxAura"), 1);
		Auras allowedAura = Auras.values()[nbt.getInteger("playerAffinity")];
		container.addAllowed(allowedAura);
		container.store(allowedAura, nbt.getInteger("playerAuraAmount"));
	}
	
	@Override
	public IMessage onMessage(MessagePlayerStats message, MessageContext ctx) {
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(Minecraft.getMinecraft().thePlayer);
		
		stats.book = message.book;
		stats.pages = message.pages;
		
		container = new AuraContainer(message.maxAura, 1);
		container.addAllowed(message.allowedAura);
		container.store(message.allowedAura, message.storedAura);
		stats.setAuraContainer(container);
		
		return null;
	}
	
	public void sendPacket(EntityPlayer player) {
		PacketHandler.wrapper.sendTo(new MessagePlayerStats(this), (EntityPlayerMP) player);
	}
	
	public static void update(EntityPlayer player) {
		AuramcraftPlayerStats stats = get(player);
		AuraContainer container = stats.getAuraContainer();
		Auras allowed = container.getAllowed()[0];
		
		// Replenish auras once every 10 seconds
		if(Math.random() <= 0.005) {
			container.store(allowed, 1);
			stats.setAuraContainer(container);
			stats.sendPacket(player);
		}
	}
	
	@Override
	public void init(Entity entity, World world) {
		
	}
	
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
