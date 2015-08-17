package com.auramcraft.network.message;

import com.auramcraft.reference.BookIds;
import com.auramcraft.stats.AuramcraftPlayerStats;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class MsgPlayerStats extends MsgAuraContainer {
	public boolean book;
	public ArrayList<byte[]> pages = new ArrayList<byte[]>();
	
	public MsgPlayerStats() {}
	
	public MsgPlayerStats(AuramcraftPlayerStats stats) {
		super(stats.getAuraContainer());
		this.book = stats.gotBook();
		
		for(int i = 0; i < BookIds.tabs; i++)
			this.pages.add(stats.getByteFromBool(stats.getPages(i)));
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		
		book = buf.readBoolean();
		
		for(int i = 0; i < BookIds.tabs; i++) {
			pages.add(new byte[BookIds.getTab(i).getPages().size()]);
			buf.readBytes(pages.get(i));
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		
		buf.writeBoolean(book);
		
		for(int i = 0; i < BookIds.tabs; i++)
			buf.writeBytes(pages.get(i));
	}
}
