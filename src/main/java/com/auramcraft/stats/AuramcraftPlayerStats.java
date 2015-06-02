package com.auramcraft.stats;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MessagePlayerStats;
import com.auramcraft.reference.PageIds;
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

public class AuramcraftPlayerStats implements IExtendedEntityProperties, IMessageHandler<MessagePlayerStats, IMessage> {
	private boolean book;
	private byte[] pages = new byte[PageIds.BOOKLENGTH];
	private AuraContainer container;
	
	public AuramcraftPlayerStats() {
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
		nbt.setByte("playerAffinity", (byte) container.getAllowed()[0].getId());
		nbt.setByte("playerMaxAura", (byte) container.getMaxAura());
		nbt.setByte("playerAuraAmount", (byte) container.getStoredAura(container.getAllowed()[0]));
		compound.setTag(Reference.MODID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(Reference.MODID);
		
		book = nbt.getBoolean("bookOfAura");
		pages = nbt.getByteArray("ResearchedPages");
		
		container = new AuraContainer(nbt.getByte("playerMaxAura"), 1);
		Auras allowedAura = Auras.values()[nbt.getByte("playerAffinity")];
		container.addAllowed(allowedAura);
		container.store(allowedAura, nbt.getByte("playerAuraAmount"));
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
