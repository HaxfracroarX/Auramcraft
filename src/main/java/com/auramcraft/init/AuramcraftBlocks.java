package com.auramcraft.init;

import com.auramcraft.block.*;
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
		alchemicalRouter = new AlchemicalRouter(),
		auraConverter = new AuraConverter();
	
	public static void init() {
		GameRegistry.registerBlock(gemstoneOre, gemstoneOre.getUnlocalizedName());
		GameRegistry.registerBlock(infusionTable, infusionTable.getUnlocalizedName());
		GameRegistry.registerBlock(aumwoodPlanks, aumwoodPlanks.getUnlocalizedName());
		GameRegistry.registerBlock(aumwoodLeaves, aumwoodLeaves.getUnlocalizedName());
		GameRegistry.registerBlock(aumwood, aumwood.getUnlocalizedName());
		GameRegistry.registerBlock(aumwoodSapling, aumwoodSapling.getUnlocalizedName());
		GameRegistry.registerBlock(auramFlower, auramFlower.getUnlocalizedName());
		GameRegistry.registerBlock(blueLightstone, blueLightstone.getUnlocalizedName());
		GameRegistry.registerBlock(auroraBlock, auroraBlock.getUnlocalizedName());
		GameRegistry.registerBlock(shadowLantern, shadowLantern.getUnlocalizedName());
		GameRegistry.registerBlock(holyLamp, holyLamp.getUnlocalizedName());
		GameRegistry.registerBlock(storageJar, storageJar.getUnlocalizedName());
		GameRegistry.registerBlock(alchemicalRouter, alchemicalRouter.getUnlocalizedName());
		GameRegistry.registerBlock(auraConverter, auraConverter.getUnlocalizedName());
		
		LogHelper.info("Initialized Blocks");
	}
}
