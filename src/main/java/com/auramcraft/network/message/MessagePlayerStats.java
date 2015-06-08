package com.auramcraft.network.message;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.BookIds;
import com.auramcraft.stats.AuramcraftPlayerStats;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class MessagePlayerStats implements IMessage {
	public boolean book;
	public ArrayList<byte[]> pages = new ArrayList<byte[]>();
	public int maxAura;
	public byte[] storedAura, allowedAuras;
	
	public MessagePlayerStats() {}
	
	public MessagePlayerStats(AuramcraftPlayerStats stats) {
		this.book = stats.gotBook();
		
		for(int i = 0; i < BookIds.tabs; i++)
			this.pages.add(stats.getByteFromBool(stats.getPages(i)));
		
		Auras[] allowedAuras = stats.getAuraContainer().getAllowed();
		storedAura = new byte[allowedAuras.length];
		this.allowedAuras = new byte[allowedAuras.length];
		for(int i = 0; i < allowedAuras.length; i++) {
			this.allowedAuras[i] = (byte) allowedAuras[i].getId();
			this.storedAura[i] = (byte) stats.getAuraContainer().getStoredAura(allowedAuras[i]);
		}
		
		this.maxAura = stats.getAuraContainer().getMaxAura();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		book = buf.readBoolean();
		
		for(int i = 0; i < BookIds.tabs; i++) {
			pages.add(new byte[BookIds.getTab(i).getPages().size()]);
			buf.readBytes(pages.get(i));
		}
		
		allowedAuras = new byte[buf.readByte()];
		buf.readBytes(allowedAuras);
		
		storedAura = new byte[buf.readByte()];
		buf.readBytes(storedAura);
		
		maxAura = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(book);
		
		for(int i = 0; i < BookIds.tabs; i++)
			buf.writeBytes(pages.get(i));
		
		buf.writeByte(allowedAuras.length);
		buf.writeBytes(allowedAuras);
		buf.writeByte(storedAura.length);
		buf.writeBytes(storedAura);
		buf.writeByte(maxAura);
	}
}
