package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
	// Base file paths
	public static final String RESOURCE_PREFIX = Reference.MODID + ":";
	public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
	public static final String GUI_SHEET_LOCATION = "textures/gui/";
	public static final String GUI_INFUSION_LOCATION = GUI_SHEET_LOCATION + "infusion/";
	public static final String GUI_AURABOOK_PAGES_LOCATION = GUI_SHEET_LOCATION + "aurabook/";
	
	/**
	 *  Block textures
	 */
	public static class Blocks {
		// Aumwood
		public static final String BLOCK_AUMWOOD_SAPLING = RESOURCE_PREFIX + "AumwoodSapling";
		public static final String BLOCK_AUMWOOD_TOP = RESOURCE_PREFIX + "AumwoodTop";
		public static final String BLOCK_AUMWOOD_SIDE = RESOURCE_PREFIX + "AumwoodSide";
		public static final String BLOCK_AUMWOOD_PLANKS = RESOURCE_PREFIX + "AumWoodPlanks";
		public static final String BLOCK_AUMWOOD_LEAVES = RESOURCE_PREFIX + "AumWoodLeaves";
		
		// Flora
		public static final String BLOCK_AURAM_FLOWER = RESOURCE_PREFIX + "AuramFlower";
		
		// Functional Blocks
		public static final String BLOCK_INFUSION_TABLE = RESOURCE_PREFIX + "infusionTable";
		
		// Ores
		public static final String BLOCK_GEMSTONE_ORE = RESOURCE_PREFIX + "GemstoneOre";
	}
	
	/**
	 *  Item textures
	 */
	public static class Items {
		// Shards
		public static final String ITEM_FIRE_SHARD = RESOURCE_PREFIX + "FireShard";
		public static final String ITEM_EARTH_SHARD = RESOURCE_PREFIX + "EarthShard";
		public static final String ITEM_WATER_SHARD = RESOURCE_PREFIX + "WaterShard";
		public static final String ITEM_AIR_SHARD = RESOURCE_PREFIX + "AirShard";
		public static final String ITEM_AURAM_SHARD = RESOURCE_PREFIX + "AuramShard";
		
		// Misc
		public static final String ITEM_GEMSTONE = RESOURCE_PREFIX + "Gemstone";
		public static final String ITEM_SACRIFICIAL_DAGGER = RESOURCE_PREFIX + "SacrificialDagger";
		public static final String ITEM_DEV_TOY = RESOURCE_PREFIX + "SacrificialDagger";
		public static final String ITEM_BOOK_OF_AURA = RESOURCE_PREFIX + "BookOfAura";
		public static final String ITEM_AURA_CRYSTAL = RESOURCE_PREFIX + "AuraCrystal";
	}
	
	/**
	 *  Model textures
	 */
	public static class Models {
		public static final ResourceLocation MODEL_INFUSION_TABLE = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "infusionTable.png");
	}
	
	/**
	 *  GUI textures
	 */
	public static class GUI {
		// Book of Aura
		public static final ResourceLocation GUI_BOOK_OF_AURA = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookGUI.png");
		
		// Book of Aura - Items
		public static final ResourceLocation GUI_BOOK_OF_AURA_CRYSTAL = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookAuraCrystal.png");
		public static final ResourceLocation GUI_BOOK_OF_AURA_SHARDS = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookElementalShards.png");
		
		// Book of Aura - Wands
		public static final ResourceLocation GUI_BOOK_OF_AURA_WAND_CAP_IRON = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookIronWandCaps.png");
		
		// Book of Aura - Alchemy
		public static final ResourceLocation GUI_BOOK_OF_AURA_ALCHEMY = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookAlchemy.png");
		
		// Book of Aura - Magika
		public static final ResourceLocation GUI_BOOK_OF_AURA_MAGIKA = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookMagika.png");
		
		// Book of Aura - Infusion
		public static final ResourceLocation GUI_BOOK_OF_AURA_INFUSION = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookInfusion.png");
		public static final ResourceLocation GUI_BOOK_OF_AURA_INFUSION_TIER_1 = new ResourceLocation(Reference.MODID, GUI_AURABOOK_PAGES_LOCATION + "AuraBookTier1Infusion.png");
		
		// Functional
		public static final ResourceLocation GUI_INFUSION_TABLE = new ResourceLocation(Reference.MODID, GUI_INFUSION_LOCATION + "infusionTable.png");
	}
}
