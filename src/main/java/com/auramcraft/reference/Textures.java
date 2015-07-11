package com.auramcraft.reference;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class Textures {
	// Base file paths
	private static final String
		RESOURCE_PREFIX = Reference.MOD_ID + ":",
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
			INFUSION_TABLE = RESOURCE_PREFIX + "infusionTable",
			AURA_CONVERTER = RESOURCE_PREFIX + "AuraConverter",
			
			// Storage
			STORAGE_JAR = RESOURCE_PREFIX + "StorageJar",
			
			// Aura Transportation
			ALCHEMICAL_ROUTER = RESOURCE_PREFIX + "Router",
			
			// Ores
			GEMSTONE_ORE = RESOURCE_PREFIX + "GemstoneOre",
			
			//Decoration
			SHADOW_LANTERN_TOP = RESOURCE_PREFIX + "ShadowLanternTop",
			SHADOW_LANTERN_SIDE = RESOURCE_PREFIX + "ShadowLanternSide",
			SHADOW_LANTERN_FRONT = RESOURCE_PREFIX + "ShadowLanternFront",
			HOLY_LAMP_SIDE = RESOURCE_PREFIX + "HolyLampSide",
			HOLY_LAMP_BOTTOM = RESOURCE_PREFIX + "HolyLampTopBottom",
			BLUE_LIGHTSTONE = RESOURCE_PREFIX + "BlueLightstone",
			AURORA_BLOCK = RESOURCE_PREFIX + "AuroraBlock",
			
			// Flora
			AURAM_FLOWER = RESOURCE_PREFIX + "AuramFlower",
			
			// Aumwood
			AUMWOOD_SAPLING = RESOURCE_PREFIX + "AumwoodSapling",
			AUMWOOD_TOP = RESOURCE_PREFIX + "AumwoodTop",
			AUMWOOD_SIDE = RESOURCE_PREFIX + "AumwoodSide",
			AUMWOOD_PLANKS = RESOURCE_PREFIX + "AumwoodPlanks",
			AUMWOOD_LEAVES = RESOURCE_PREFIX + "AumwoodLeaves";
	}
	
	/**
	 *  Item textures
	 */
	public static class Items {
		public static final String 
			// Shards
			FIRE_SHARD = RESOURCE_PREFIX + "FireShard",
			EARTH_SHARD = RESOURCE_PREFIX + "EarthShard",
			WATER_SHARD = RESOURCE_PREFIX + "WaterShard",
			AIR_SHARD = RESOURCE_PREFIX + "AirShard",
			AURAM_SHARD = RESOURCE_PREFIX + "AuramShard",
			
			// Allseeing
			ALLSEEING_STONE = RESOURCE_PREFIX + "AllseeingStone",
			CHARM_OF_ALLSEEING = RESOURCE_PREFIX + "AllseeingCharm",
			
			// Misc
			GEMSTONE = RESOURCE_PREFIX + "Gemstone",
			SACRIFICIAL_DAGGER = RESOURCE_PREFIX + "SacrificialDagger",
			DEV_TOY = RESOURCE_PREFIX + "SacrificialDagger",
			BOOK_OF_AURA = RESOURCE_PREFIX + "BookOfAura",
			AURA_CRYSTAL = RESOURCE_PREFIX + "AuraCrystal";
		
		/*  Wands   */
		// Wand IDs
		public static final int 
			WAND_EMPTY = 0,
			WAND_CORE_AUMWOOD = 1,
			WAND_CORE_DIAMOND = 2,
			WAND_CORE_INFUSED = 3,
			WAND_CAP_GEMSTONE = 1,
			WAND_CAP_IRON = 2,
			WAND_CAP_GOLD = 3,
			WAND_CAP_DIAMOND = 4,
			WAND_CAP_INFUSED = 5,
			WAND_CAP_MAGIC = 6,
			WAND_CLOTH_INFUSED = 1,
			WAND_CLOTH_MAGIC = 2;
		
		// Wand Cores
		public static final String[] wandCores = {
			//WAND_EMPTY
			WAND_TEXTURE_LOCATION + "empty",
			//WAND_CORE_AUMWOOD
			WAND_TEXTURE_LOCATION + "Auramwood",
			//WAND_CORE_DIAMOND
			WAND_TEXTURE_LOCATION + "Diamond", 
			//WAND_CORE_INFUSED
			WAND_TEXTURE_LOCATION + "Infused"
		};
		
		// Wand Caps
		public static final String[] wandCaps = {
			//WAND_EMPTY
			WAND_TEXTURE_LOCATION + "empty",
			//WAND_CAP_GEMSTONE
			WAND_TEXTURE_LOCATION + "GemstoneCap",
			//WAND_CAP_IRON
			WAND_TEXTURE_LOCATION + "IronCap",
			//WAND_CAP_GOLD
			WAND_TEXTURE_LOCATION + "GoldCap",
			//WAND_CAP_DIAMOND
			WAND_TEXTURE_LOCATION + "DiamondCap",
			//WAND_CAP_INFUSED
			WAND_TEXTURE_LOCATION + "InfusedCap",
			//WAND_CAP_MAGIC
			WAND_TEXTURE_LOCATION + "MagicCap"
		};
		
		// Wand Cloth
		public static final String[] wandCloths = {
			//WAND_EMPTY
			WAND_TEXTURE_LOCATION + "empty",
			//WAND_CLOTH_INFUSED
			WAND_TEXTURE_LOCATION + "InfusedCloth",
			//WAND_CLOTH_MAGIC
			WAND_TEXTURE_LOCATION + "MagicCloth"
		};
		
		// Wand Icons
		public static IIcon[] 
			wandCoreOverlayIcons = new IIcon[wandCores.length],
			wandCapOverlayIcons = new IIcon[wandCaps.length],
			wandClothOverlayIcons = new IIcon[wandCloths.length],
			wandCapItemIcons = new IIcon[wandCaps.length];
	}
	
	/**
	 *  Model textures
	 */
	public static class Models {
		public static final ResourceLocation 
			INFUSION_TABLE = new ResourceLocation(Reference.MOD_ID, MODEL_TEXTURE_LOCATION + "infusionTable.png"),
			STORAGE_JAR = new ResourceLocation(Reference.MOD_ID, MODEL_TEXTURE_LOCATION + "StorageJar.png"),
			ALCHEMICAL_ROUTER = new ResourceLocation(Reference.MOD_ID, MODEL_TEXTURE_LOCATION + "Router.png");
	}
	
	/**
	 *  GUI textures
	 */
	public static class GUI {
		public static final ResourceLocation 
			// Book of Aura
			BOOK_OF_AURA = new ResourceLocation(Reference.MOD_ID, GUI_AURABOOK_LOCATION + "AuraBookGUI.png"),
			PAGE_GET = new ResourceLocation(Reference.MOD_ID, GUI_AURABOOK_LOCATION + "PageGet.png"),
			
			INFUSION_TABLE = new ResourceLocation(Reference.MOD_ID, GUI_INFUSION_LOCATION + "infusionTable.png"),
			AURA_CONVERTER = new ResourceLocation(Reference.MOD_ID, GUI_SHEET_LOCATION + "AuraConverter.png");
	}
}
