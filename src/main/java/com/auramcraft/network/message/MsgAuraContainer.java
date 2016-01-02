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
		Auras[] allowed = container.getAllowed();
		Auras[] auras = Auras.values();
		
		if(allowed.length == 0) {
			// All Auras are allowed
			storedAura = new byte[0];
			allowedAuras = new byte[auras.length];
			for(int i = 0; i < storedAura.length; i++) {
				storedAura[i] = (byte) container.getStoredAura(auras[i]);
			}
		}
		else {
			// Only some Auras are allowed
			storedAura = new byte[allowed.length];
			allowedAuras = new byte[allowed.length];
			for(int i = 0; i < storedAura.length; i++) {
				allowedAuras[i] = (byte) allowed[i].getId();
				storedAura[i] = (byte) container.getStoredAura(allowed[i]);
			}
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
