package com.auramcraft.network.message;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MsgAuraContainer implements IMessage {
	public int maxAura;
	public byte[] storedAura, allowedAuras;
	
	public MsgAuraContainer() {}
	
	public MsgAuraContainer(AuraContainer container) {
		Auras[] allowedAuras = container.getAllowed();
		storedAura = new byte[allowedAuras.length];
		this.allowedAuras = new byte[allowedAuras.length];
		for(int i = 0; i < allowedAuras.length; i++) {
			this.allowedAuras[i] = (byte) allowedAuras[i].getId();
			this.storedAura[i] = (byte) container.getStoredAura(allowedAuras[i]);
		}
		
		this.maxAura = container.getMaxAura();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		allowedAuras = new byte[buf.readByte()];
		buf.readBytes(allowedAuras);
		storedAura = new byte[buf.readByte()];
		buf.readBytes(storedAura);
		maxAura = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(allowedAuras.length);
		buf.writeBytes(allowedAuras);
		buf.writeByte(storedAura.length);
		buf.writeBytes(storedAura);
		buf.writeInt(maxAura);
	}
}
