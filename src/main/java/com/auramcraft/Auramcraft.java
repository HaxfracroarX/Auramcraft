package com.auramcraft;

import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.init.AuramcraftGen;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.init.AuramcraftRecipes;
import com.auramcraft.network.PacketHandler;
import com.auramcraft.proxy.IProxy;
import com.auramcraft.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Auramcraft {
	@Instance(Reference.MODID)
	public static Auramcraft instance;
	
	@SuppressWarnings("WeakerAccess")
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Packets
		PacketHandler.init();
		
		// Items
		AuramcraftItems.init();
		
		// Blocks
		AuramcraftBlocks.init();
		
		// Generation
		AuramcraftGen.init();
		
		// Recipes
		AuramcraftRecipes.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register GUI Handlers
		proxy.registerGUIHandlers();
		
		// Initialize tile entities
		proxy.registerTileEntities();
		
		// Initialize renderers
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Register Event Handlers
		proxy.registerEventHandlers();
	}
}
