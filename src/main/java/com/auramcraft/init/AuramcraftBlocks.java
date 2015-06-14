package com.auramcraft.init;

import com.auramcraft.block.*;
import com.auramcraft.reference.Names;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@SuppressWarnings("WeakerAccess")
public class AuramcraftBlocks {
	public static final Block 
		gemstoneOre = new GemstoneOre(),
		infusionTable = new InfusionTable(),
		aumwoodPlanks = new AumwoodPlanks(),
		aumwoodLeaves = new AumwoodLeaves(),
		aumwood = new Aumwood(),
		aumwoodSapling = new AumwoodSapling(),
		auramFlower = new AuramFlower(),
		blueLightstone = new BlueLightstone(),
		auroraBlock = new AuroraBlock(),
		shadowLantern = new ShadowLantern(),
		holyLamp = new HolyLamp(),
		storageJar = new StorageJar(),
		alchemicalRouter = new AlchemicalRouter();
	
	public static void init() {
		GameRegistry.registerBlock(gemstoneOre, Names.Blocks.GEMSTONE_ORE);
		GameRegistry.registerBlock(infusionTable, Names.Blocks.INFUSION_TABLE);
		GameRegistry.registerBlock(aumwoodPlanks, Names.Blocks.AUMWOOD_PLANKS);
		GameRegistry.registerBlock(aumwoodLeaves, Names.Blocks.AUMWOOD_LEAVES);
		GameRegistry.registerBlock(aumwood, Names.Blocks.AUMWOOD);
		GameRegistry.registerBlock(aumwoodSapling, Names.Blocks.AUMWOOD_SAPLING);
		GameRegistry.registerBlock(auramFlower, Names.Blocks.AURAM_FLOWER);
		GameRegistry.registerBlock(blueLightstone, Names.Blocks.BLUE_LIGHTSTONE);
		GameRegistry.registerBlock(auroraBlock, Names.Blocks.AURORA_BLOCK);
		GameRegistry.registerBlock(shadowLantern, Names.Blocks.SHADOW_LANTERN);
		GameRegistry.registerBlock(holyLamp, Names.Blocks.HOLY_LAMP);
		GameRegistry.registerBlock(storageJar, Names.Blocks.STORAGE_JAR);
		GameRegistry.registerBlock(alchemicalRouter, Names.Blocks.ALCHEMICAL_ROUTER);
		
		LogHelper.info("Initialized Blocks");
	}
}
