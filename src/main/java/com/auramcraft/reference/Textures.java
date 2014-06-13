package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
	public static final String RESOURCE_PREFIX = Reference.MODID + ":";
	
	// Base file paths
	public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
	public static final String GUI_SHEET_LOCATION = "textures/gui/";
	
	// Model textures
	public static final ResourceLocation MODEL_INFUSION_TABLE = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "infusionTable.png");
	
	// GUI textures
	public static final ResourceLocation GUI_INFUSION_TABLE = new ResourceLocation(Reference.MODID, GUI_SHEET_LOCATION + "infusionTable.png");
}
