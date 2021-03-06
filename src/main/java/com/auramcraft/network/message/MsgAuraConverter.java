package com.auramcraft.network.message;

import com.auramcraft.api.Auras;
import com.auramcraft.tileentity.TileAuraConverter;
import io.netty.buffer.ByteBuf;

public class MsgAuraConverter extends MsgTileAuramcraft {
	public Auras input, output;
	
	public MsgAuraConverter() {}
	
	public MsgAuraConverter(TileAuraConverter tileEntity) {
		super(tileEntity);
		input = tileEntity.getInput();
		output = tileEntity.getOutput();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		input = Auras.values()[buf.readInt()];
		output = Auras.values()[buf.readInt()];
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(input.ordinal());
		buf.writeInt(output.ordinal());
	}
}
