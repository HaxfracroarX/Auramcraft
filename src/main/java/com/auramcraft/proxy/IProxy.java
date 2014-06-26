package com.auramcraft.proxy;

public interface IProxy {
	public abstract void registerTileEntities();
	
	public abstract void registerRenderers();
	
	public abstract void registerEventHandlers();
	
	public abstract void registerGUIHandlers();
}
