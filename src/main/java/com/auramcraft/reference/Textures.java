package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
	// Base file paths
	private static final String
		RESOURCE_PREFIX = Reference.MODID + ":",
		MODEL_TEXTURE_LOCATION = "textures/models/",
		WAND_TEXTURE_LOCATION = RESOURCE_PREFIX + "wands/",
		GUI_SHEET_LOCATION = "textures/gui/",
		GUI_INFUSION_LOCATION = GUI_SHEET_LOCATION + "infusion/",
		GUI_AURABOOK_LOCATION = GUI_SHEET_LOCATION + "aurabook/";
	
	/**
	 *  Block textures
	 */
	public static class Blocks {
		
		public static final String 
			// Machines
			BLOCK_INFUSION_TABLE = RESOURCE_PREFIX + "infusionTable",
			
			// Storage
			BLOCK_STORAGE_JAR = RESOURCE_PREFIX + "StorageJar",
			
			// Aura Transportation
			BLOCK_ALCHEMICAL_ROUTER = RESOURCE_PREFIX + "Router",
			
			// Ores
			BLOCK_GEMSTONE_ORE = RESOURCE_PREFIX + "GemstoneOre",
			
			//Decoration
			BLOCK_SHADOW_LANTERN_TOP = RESOURCE_PREFIX + "ShadowLanternTop",
			BLOCK_SHADOW_LANTERN_SIDE = RESOURCE_PREFIX + "ShadowLanternSide",
			BLOCK_SHADOW_LANTERN_FRONT = RESOURCE_PREFIX + "ShadowLanternFront",
			BLOCK_HOLY_LAMP_SIDE = RESOURCE_PREFIX + "HolyLampSide",
			BLOCK_HOLY_LAMP_BOTTOM = RESOURCE_PREFIX + "HolyLampTopBottom",
			BLOCK_BLUE_LIGHTSTONE = RESOURCE_PREFIX + "BlueLightstone",
			BLOCK_AURORA_BLOCK = RESOURCE_PREFIX + "AuroraBlock",
			
			// Flora
			BLOCK_AURAM_FLOWER = RESOURCE_PREFIX + "AuramFlower",
			
			// Aumwood
			BLOCK_AUMWOOD_SAPLING = RESOURCE_PREFIX + "AumwoodSapling",
			BLOCK_AUMWOOD_TOP = RESOURCE_PREFIX + "AumwoodTop",
			BLOCK_AUMWOOD_SIDE = RESOURCE_PREFIX + "AumwoodSide",
			BLOCK_AUMWOOD_PLANKS = RESOURCE_PREFIX + "AumwoodPlanks",
			BLOCK_AUMWOOD_LEAVES = RESOURCE_PREFIX + "AumwoodLeaves";
	}
	
	/**
	 *  Item textures
	 */
	public static class Items {
		public static final String 
			// Shards
			ITEM_FIRE_SHARD = RESOURCE_PREFIX + "FireShard",
			ITEM_EARTH_SHARD = RESOURCE_PREFIX + "EarthShard",
			ITEM_WATER_SHARD = RESOURCE_PREFIX + "WaterShard",
			ITEM_AIR_SHARD = RESOURCE_PREFIX + "AirShard",
			ITEM_AURAM_SHARD = RESOURCE_PREFIX + "AuramShard",
			
			// Allseeing
			ITEM_ALLSEEING_STONE = RESOURCE_PREFIX + "AllseeingStone",
			ITEM_CHARM_OF_ALLSEEING = RESOURCE_PREFIX + "AllseeingCharm",
			
			// Wands
			WAND_EMPTY = WAND_TEXTURE_LOCATION + "empty",
			
			// Wand Cores
			WAND_CORE_AURAMWOOD = WAND_TEXTURE_LOCATION + "AuramwoodUnderlay",
			WAND_CORE_DIAMOND = WAND_TEXTURE_LOCATION + "DiamondUnderlay",
			WAND_CORE_INFUSED = WAND_TEXTURE_LOCATION + "InfusedUnderlay",
			
			// Wand Caps
			WAND_CAP_DIAMOND = WAND_TEXTURE_LOCATION + "DiamondCapOverlay",
			WAND_CAP_GEMSTONE = WAND_TEXTURE_LOCATION + "GemstoneCapOverlay",
			WAND_CAP_GOLD = WAND_TEXTURE_LOCATION + "GoldCapOverlay",
			WAND_CAP_INFUSED = WAND_TEXTURE_LOCATION + "InfusedMagicCapOverlay",
			WAND_CAP_IRON = WAND_TEXTURE_LOCATION + "IronCapOverlay",
			WAND_CAP_MAGIC = WAND_TEXTURE_LOCATION + "MagicCapOverlay",
			
			// Wand Cloth
			WAND_CLOTH_INFUSED = WAND_TEXTURE_LOCATION + "InfusedClothOverlay",
			WAND_CLOTH_MAGIC = WAND_TEXTURE_LOCATION + "MagicClothOverlay",
			
			// Misc
			ITEM_GEMSTONE = RESOURCE_PREFIX + "Gemstone",
			ITEM_SACRIFICIAL_DAGGER = RESOURCE_PREFIX + "SacrificialDagger",
			ITEM_DEV_TOY = RESOURCE_PREFIX + "SacrificialDagger",
			ITEM_BOOK_OF_AURA = RESOURCE_PREFIX + "BookOfAura",
			ITEM_AURA_CRYSTAL = RESOURCE_PREFIX + "AuraCrystal";
	}
	
	/**
	 *  Model textures
	 */
	public static class Models {
		public static final ResourceLocation 
			MODEL_INFUSION_TABLE = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "infusionTable.png"),
			MODEL_STORAGE_JAR = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "StorageJar.png"),
			MODEL_ALCHEMICAL_ROUTER = new ResourceLocation(Reference.MODID, MODEL_TEXTURE_LOCATION + "Router.png");
	}
	
	/**
	 *  GUI textures
	 */
	public static class GUI {
		public static final ResourceLocation 
			// Book of Aura
			GUI_BOOK_OF_AURA = new ResourceLocation(Reference.MODID, GUI_AURABOOK_LOCATION + "AuraBookGUI.png"),
			GUI_PAGE_GET = new ResourceLocation(Reference.MODID, GUI_AURABOOK_LOCATION + "PageGet.png"),
			
			// Infusion Table
			GUI_INFUSION_TABLE = new ResourceLocation(Reference.MODID, GUI_INFUSION_LOCATION + "infusionTable.png");
	}
}
