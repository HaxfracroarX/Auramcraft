package com.auramcraft.reference;

import net.minecraft.util.ResourceLocation;

public class PageData {
	private static final String PAGES_LOCATION = "lang/pages/";
	
	public static final ResourceLocation
		ALCHEMY = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "alchemy.txt"),
		AURACRYSTAL = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "auracrystal.txt"),
		EMPTY = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "empty.txt"),
		INFUSION = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "infusion.txt"),
		INFUSION_TIER_1 = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "infusion_tier1.txt"),
		MAGIKA = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "magika.txt"),
		SHARDS = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "shards.txt"),
		WAND_CAP_IRON = new ResourceLocation(Reference.MODID, PAGES_LOCATION + "wand_cap_iron.txt");
}
