package com.auramcraft.network.message;

import com.auramcraft.api.Auras;
import com.auramcraft.reference.PageIds;
import com.auramcraft.stats.AuramcraftPlayerStats;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessagePlayerStats implements IMessage {
	public boolean book;
	public byte[] pages = new byte[PageIds.BOOKLENGTH];
	public Auras allowedAura;
	public int storedAura, maxAura;
	
	public MessagePlayerStats() {}
	
	public MessagePlayerStats(AuramcraftPlayerStats stats) {
		this.book = stats.gotBook();
		this.pages = stats.getByteFromBool(stats.getPages());
		this.allowedAura = stats.getAuraContainer().getAllowed()[0];
		this.storedAura = stats.getAuraContainer().getStoredAura(allowedAura);
		this.maxAura = stats.getAuraContainer().getMaxAura();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		book = buf.readBoolean();
		buf.readBytes(pages);
		allowedAura = Auras.values()[buf.readByte()];
		storedAura = buf.readByte();
		maxAura = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(book);
		buf.writeBytes(pages);
		buf.writeByte(allowedAura.getId());
		buf.writeByte(storedAura);
		buf.writeByte(maxAura);
	}
}
