package com.auramcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import com.auramcraft.creativetab.*;
import com.auramcraft.generation.*;
import com.auramcraft.handler.*;
import com.auramcraft.init.*;
import com.auramcraft.item.*;
import com.auramcraft.network.*;
import com.auramcraft.proxy.*;
import com.auramcraft.reference.*;
import com.auramcraft.tileentity.*;
import com.auramcraft.util.*;
import com.auramcraft.block.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Auramcraft {
	@Instance(Reference.MODID)
	public static Auramcraft instance;
	
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
		
		// Achievements
		AuramcraftAchievements.init();
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