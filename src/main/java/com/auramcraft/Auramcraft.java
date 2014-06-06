package com.auramcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "Auramcraft", name = "Auramcraft", version = "0.0.1")
public class Auramcraft {
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Initializing Auramcraft");
		
		// TODO
		
		System.out.println("Initialized Auramcraft");
	}
}