package com.auramcraft.init;

import net.minecraft.launchwrapper.LogWrapper;
import sun.util.logging.resources.logging;
import com.auramcraft.generation.*;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftGen {
	public static final OreGeneration oreGeneration = new OreGeneration();
	
	public static void init() {
		GameRegistry.registerWorldGenerator(oreGeneration, 10);
		
		LogHelper.info("Initialized Generation");
	}
}
