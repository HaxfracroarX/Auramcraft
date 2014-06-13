package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
	public static final String RESOURCE_PREFIX = Reference.MODID + ":";
	
	// Base file paths
	public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
	public static final String GUI_SHEET_LOCATION = "textures/gui/";
	
	// Blocks
	public static final String BLOCK_GEMSTONE_ORE = RESOURCE_PREFIX + "GemstoneOre";
	public static final String BLOCK_INFUSION_TABLE = RESOURCE_PREFIX + "infusionTable";
	
	// Items
	public static final String ITEM_FIRE_SHARD = RESOURCE_PREFIX + "FireShard";
	public static final String ITEM_EARTH_SHARD = RESOURCE_PREFIX + "EarthShard";
	public static final String ITEM_WATER_SHARD = RESOURCE_PREFIX + "WaterShard";
	public static final String ITEM_AIR_SHARD = RESOURCE_PREFIX + "AirShard";
	public static final String ITEM_AURAM_SHARD = RESOURCE_PREFIX + "AuramShard";
	public static final String ITEM_GEMSTONE = RESOURCE_PREFIX + "Gemstone";
	public static final String ITEM_SACRIFICIAL_DAGGER = RESOURCE_PREFIX + "SacrificialDagger";
	
	// Model textures
	public static final ResourceLocation MODEL_INFUSION_TABLE = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "infusionTable.png");
	
	// GUI textures
	public static final ResourceLocation GUI_INFUSION_TABLE = new ResourceLocation(Reference.MODID, GUI_SHEET_LOCATION + "infusionTable.png");
}
