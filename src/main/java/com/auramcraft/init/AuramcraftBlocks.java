package com.auramcraft.init;

import net.minecraft.block.Block;
import com.auramcraft.block.*;
import com.auramcraft.reference.Names;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

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
		GameRegistry.registerBlock(storageJar, Names.Blocks.STORAGEJAR);
		GameRegistry.registerBlock(alchemicalRouter, Names.Blocks.ALCHEMICALROUTER);
		
		LogHelper.info("Initialized Blocks");
	}
}
