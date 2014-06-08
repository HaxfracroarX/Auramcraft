package com.auramcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.auramcraft.creativetab.*;
import com.auramcraft.item.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Auramcraft.MODID, 
	 name = "Auramcraft", 
	 version = Auramcraft.VERSION)
public class Auramcraft {
	public static final String MODID = "auramcraft";
	public static final String VERSION = "0.0.1";
	
	// Creative Tab
	public static CreativeTabs auramcraftTab = new AuramcraftTab("Auramcraft");
	
	// Shards
	public static Item fireShard;
	public static Item earthShard;
	public static Item waterShard;
	public static Item airShard;
	public static Item auramShard;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("Initializing Auramcraft");
		
		// Shards
		fireShard = new FireShard()
				.setUnlocalizedName("fireShard")
				.setTextureName(Auramcraft.MODID + ":FireShard");
		GameRegistry.registerItem(fireShard, fireShard.getUnlocalizedName());
		System.out.println("Initialized: " + fireShard.getUnlocalizedName());
		earthShard = new EarthShard()
				.setUnlocalizedName("earthShard")
				.setTextureName(Auramcraft.MODID + ":EarthShard");
		GameRegistry.registerItem(earthShard, earthShard.getUnlocalizedName());
		System.out.println("Initialized: " + earthShard.getUnlocalizedName());
		waterShard = new WaterShard()
				.setUnlocalizedName("waterShard")
				.setTextureName(Auramcraft.MODID + ":WaterShard");
		GameRegistry.registerItem(waterShard, waterShard.getUnlocalizedName());
		System.out.println("Initialized: " + waterShard.getUnlocalizedName());
		airShard = new AirShard()
				.setUnlocalizedName("airShard")
				.setTextureName(Auramcraft.MODID + ":AirShard");
		GameRegistry.registerItem(airShard, airShard.getUnlocalizedName());
		System.out.println("Initialized: " + airShard.getUnlocalizedName());
		auramShard = new AuramShard()
				.setUnlocalizedName("auramShard")
				.setTextureName(Auramcraft.MODID + ":AuramShard");
		GameRegistry.registerItem(auramShard, auramShard.getUnlocalizedName());
		System.out.println("Initialized: " + auramShard.getUnlocalizedName());
		
		System.out.println("Initialized Auramcraft");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
}