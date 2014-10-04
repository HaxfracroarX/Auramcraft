package com.auramcraft.init;

import net.minecraft.block.Block;

import com.auramcraft.block.*;
import com.auramcraft.reference.Names;
import com.auramcraft.util.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftBlocks {
	public static final Block gemstoneOre = new GemstoneOre();
	public static final Block infusionTable = new InfusionTable();
	public static final Block aumwoodPlanks = new AumwoodPlanks();
	public static final Block aumwoodLeaves = new AumwoodLeaves();
	public static final Block aumwood = new Aumwood();
	public static final Block aumwoodSapling = new AumwoodSapling();
	public static final Block auramFlower = new AuramFlower();
	public static final Block blueLightstone = new BlueLightstone();
	public static final Block auroraBlock = new AuroraBlock();
	public static final Block shadowLantern = new ShadowLantern();
	public static final Block holyLamp = new HolyLamp();
	
	public static void init() {
		GameRegistry.registerBlock(gemstoneOre, Names.Blocks.GEMSTONEORE);
		GameRegistry.registerBlock(infusionTable, Names.Blocks.INFUSIONTABLE);
		GameRegistry.registerBlock(aumwoodPlanks, Names.Blocks.AUMWOODPLANKS);
		GameRegistry.registerBlock(aumwoodLeaves, Names.Blocks.AUMWOODLEAVES);
		GameRegistry.registerBlock(aumwood, Names.Blocks.AUMWOOD);
		GameRegistry.registerBlock(aumwoodSapling, Names.Blocks.AUMWOODSAPLING);
		GameRegistry.registerBlock(auramFlower, Names.Blocks.AURAMFLOWER);
		GameRegistry.registerBlock(blueLightstone, Names.Blocks.BLUELIGHTSTONE);
		GameRegistry.registerBlock(auroraBlock, Names.Blocks.AURORABLOCK);
		GameRegistry.registerBlock(shadowLantern, Names.Blocks.SHADOWLANTERN);
		GameRegistry.registerBlock(holyLamp, Names.Blocks.HOLYLAMP);
		
		LogHelper.info("Initialized Blocks");
	}
}
