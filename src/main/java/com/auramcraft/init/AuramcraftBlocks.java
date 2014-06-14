package com.auramcraft.init;

import net.minecraft.block.Block;
import com.auramcraft.block.*;
import com.auramcraft.reference.Names;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftBlocks {
	public static final Block gemstoneOre = new GemstoneOre();
	public static final Block infusionTable = new InfusionTable();
	public static final Block aumWoodPlanks = new AumWoodPlanks();
	public static final Block aumWoodLeaves = new AumWoodLeaves();
	
	public static void init() {
		GameRegistry.registerBlock(gemstoneOre, Names.Blocks.GEMSTONEORE);
		GameRegistry.registerBlock(infusionTable, Names.Blocks.INFUSIONTABLE);
		GameRegistry.registerBlock(aumWoodPlanks, Names.Blocks.AUMWOODPLANKS);
		GameRegistry.registerBlock(aumWoodLeaves, Names.Blocks.AUMWOODLEAVES);
		
		LogHelper.info("Initialized Blocks");
	}
}
