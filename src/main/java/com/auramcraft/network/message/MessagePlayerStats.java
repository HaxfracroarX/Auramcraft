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
	public Auras allowedAura;
	public int storedAura, maxAura;
	
	public MessagePlayerStats() {}
	
	public MessagePlayerStats(AuramcraftPlayerStats stats) {
		this.book = stats.gotBook();
		
		for(int i = 0; i < BookIds.tabs; i++)
			this.pages.add(stats.getByteFromBool(stats.getPages(i)));
		
		this.allowedAura = stats.getAuraContainer().getAllowed()[0];
		this.storedAura = stats.getAuraContainer().getStoredAura(allowedAura);
		this.maxAura = stats.getAuraContainer().getMaxAura();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		book = buf.readBoolean();
		
		for(int i = 0; i < BookIds.tabs; i++) {
			pages.add(new byte[BookIds.getTab(i).getPages().size()]);
			buf.readBytes(pages.get(i));
		}
		
		allowedAura = Auras.values()[buf.readByte()];
		storedAura = buf.readByte();
		maxAura = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(book);
		
		for(int i = 0; i < BookIds.tabs; i++)
			buf.writeBytes(pages.get(i));
		
		buf.writeByte(allowedAura.getId());
		buf.writeByte(storedAura);
		buf.writeByte(maxAura);
	}
}
