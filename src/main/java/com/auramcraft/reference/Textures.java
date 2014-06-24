package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
	public static final String RESOURCE_PREFIX = Reference.MODID + ":";
	
	// Base file paths
	public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
	public static final String GUI_SHEET_LOCATION = "textures/gui/";
	public static final String GUI_AURABOOK_PAGES_LOCATION = GUI_SHEET_LOCATION + "aurabook/";
	
	// Blocks
	public static class Blocks {
		public static final String BLOCK_GEMSTONE_ORE = RESOURCE_PREFIX + "GemstoneOre";
		public static final String BLOCK_INFUSION_TABLE = RESOURCE_PREFIX + "infusionTable";
		public static final String BLOCK_AUMWOOD_PLANKS = RESOURCE_PREFIX + "AumWoodPlanks";
		public static final String BLOCK_AUMWOOD_LEAVES = RESOURCE_PREFIX + "AumWoodLeaves";
		public static final String BLOCK_AUMWOOD_TOP = RESOURCE_PREFIX + "AumwoodTop";
		public static final String BLOCK_AUMWOOD_SIDE = RESOURCE_PREFIX + "AumwoodSide";
		public static final String BLOCK_AUMWOOD_SAPLING = RESOURCE_PREFIX + "AumwoodSapling";
		public static final String BLOCK_AURAM_FLOWER = RESOURCE_PREFIX + "AuramFlower";
	}
	
	// Items
	public static class Items {
		public static final String ITEM_FIRE_SHARD = RESOURCE_PREFIX + "FireShard";
		public static final String ITEM_EARTH_SHARD = RESOURCE_PREFIX + "EarthShard";
		public static final String ITEM_WATER_SHARD = RESOURCE_PREFIX + "WaterShard";
		public static final String ITEM_AIR_SHARD = RESOURCE_PREFIX + "AirShard";
		public static final String ITEM_AURAM_SHARD = RESOURCE_PREFIX + "AuramShard";
		public static final String ITEM_GEMSTONE = RESOURCE_PREFIX + "Gemstone";
		public static final String ITEM_SACRIFICIAL_DAGGER = RESOURCE_PREFIX + "SacrificialDagger";
		public static final String ITEM_DEV_TOY = RESOURCE_PREFIX + "SacrificialDagger";
		public static final String ITEM_BOOK_OF_AURA = RESOURCE_PREFIX + "BookOfAura";
	}
	
	// Model textures
	public static class Models {
		public static final ResourceLocation MODEL_INFUSION_TABLE = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "infusionTable.png");
	}
	
	// GUI textures
	public static class GUI {
		public static final ResourceLocation GUI_INFUSION_TABLE = new ResourceLocation(Reference.MODID, GUI_SHEET_LOCATION + "infusionTable.png");
		public static final ResourceLocation GUI_BOOK_OF_AURA = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookGUI.png");
	}
}
