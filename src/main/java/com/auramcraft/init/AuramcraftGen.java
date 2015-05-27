package com.auramcraft.init;

import com.auramcraft.generation.*;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
public class AuramcraftGen {
	public static final OreGeneration oreGeneration = new OreGeneration();
	public static final AumwoodTreeGen auramwoodWorldGenTree = new AumwoodTreeGen();
	
	public static void init() {
		GameRegistry.registerWorldGenerator(oreGeneration, 10);
		GameRegistry.registerWorldGenerator(auramwoodWorldGenTree, 10);
		
		LogHelper.info("Initialized Generation");
	}
}
