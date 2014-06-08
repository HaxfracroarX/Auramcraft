package com.auramcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import com.auramcraft.creativetab.*;
import com.auramcraft.generation.*;
import com.auramcraft.item.*;
import com.auramcraft.block.*;
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
	
	// Ore
	public static Block gemstoneOre;
	public static Item gemstone;
	
	// Generation
	public static OreGeneration oreGeneration;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("Initializing Auramcraft");
		
		// Shards
		fireShard = new FireShard();
		GameRegistry.registerItem(fireShard, fireShard.getUnlocalizedName());
		System.out.println("Initialized: " + fireShard.getUnlocalizedName());
		earthShard = new EarthShard();
		GameRegistry.registerItem(earthShard, earthShard.getUnlocalizedName());
		System.out.println("Initialized: " + earthShard.getUnlocalizedName());
		waterShard = new WaterShard();
		GameRegistry.registerItem(waterShard, waterShard.getUnlocalizedName());
		System.out.println("Initialized: " + waterShard.getUnlocalizedName());
		airShard = new AirShard();
		GameRegistry.registerItem(airShard, airShard.getUnlocalizedName());
		System.out.println("Initialized: " + airShard.getUnlocalizedName());
		auramShard = new AuramShard();
		GameRegistry.registerItem(auramShard, auramShard.getUnlocalizedName());
		System.out.println("Initialized: " + auramShard.getUnlocalizedName());
		
		// Ore
		gemstoneOre = new GemstoneOre();
		GameRegistry.registerBlock(gemstoneOre, gemstoneOre.getUnlocalizedName());
		System.out.println("Initialized: " + gemstoneOre.getUnlocalizedName());
		gemstone = new Gemstone();
		GameRegistry.registerItem(gemstone, gemstone.getUnlocalizedName());
		System.out.println("Initialized: " + gemstone.getUnlocalizedName());
		
		// Generation
		oreGeneration = new OreGeneration();
		GameRegistry.registerWorldGenerator(oreGeneration, 1);
		
		System.out.println("Initialized Auramcraft");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
}