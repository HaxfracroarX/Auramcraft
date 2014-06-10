package com.auramcraft;

//Xydium here, making sure Git is behaving. Ignore this comment

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
import com.auramcraft.proxy.*;
import com.auramcraft.reference.Reference;
import com.auramcraft.tileentity.*;
import com.auramcraft.util.LogHelper;
import com.auramcraft.block.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
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
		LogHelper.info("Initializing Auramcraft");
		
		// Items
		AuramcraftItems.init();
		
		// Blocks
		AuramcraftBlocks.init();
		
		// Generation
		AuramcraftGen.init();
		
		LogHelper.info("Initialized Auramcraft");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register GUI Handler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		// Initialize tile entities
		proxy.registerTileEntities();
	}
}