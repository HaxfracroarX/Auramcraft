package com.auramcraft.reference;

import com.auramcraft.client.gui.inventory.BookPage;
import com.auramcraft.client.gui.inventory.Tab;
import com.auramcraft.util.LogHelper;

import java.util.HashMap;
import java.util.Map;

public class BookIds {
	public static int tabs = 6;
	public static Tab 
		artifacts,
		alchemy,
		infusion,
		wands,
		magika,
		misc;
	
	public static BookPage pageEmpty = new BookPage(PageData.EMPTY, -1);
	
	// Artifacts
	public static BookPage 
		pageAuraCrystal = new BookPage(PageData.AURACRYSTAL, 0);
	
	// Alchemy
	public static BookPage 
		pageAlchemy = new BookPage(PageData.ALCHEMY, 0);
	
	// Infusion
	public static BookPage 
		pageInfusion = new BookPage(PageData.INFUSION, 0),
		pageInfusionT1 = new BookPage(PageData.INFUSION_TIER_1, 1);
	
	// Wands
	public static BookPage 
		pageWandCapIron = new BookPage(PageData.WAND_CAP_IRON, 0);
	
	// Magika
	public static BookPage 
		pageMagika = new BookPage(PageData.MAGIKA, 0);
	
	// Misc
	public static BookPage 
		pageShards = new BookPage(PageData.SHARDS, 0);
	
	private static Map<Integer, Tab> tabMap = new HashMap<Integer, Tab>();
	
	public static void init() {
		artifacts = new Tab(
			"Artifacts",
			pageAuraCrystal
		);
		alchemy = new Tab(
			"Alchemy",
			pageAlchemy
		);
		infusion = new Tab(
			"Infusion",
			pageInfusion,
			pageInfusionT1
		);
		wands = new Tab(
			"Wands",
			pageWandCapIron
		);
		magika = new Tab(
			"Magika",
			pageMagika
		);
		misc = new Tab(
			"Misc",
			pageShards
		);
		
		tabMap.put(0, artifacts);
		tabMap.put(1, alchemy);
		tabMap.put(2, infusion);
		tabMap.put(3, wands);
		tabMap.put(4, magika);
		tabMap.put(5, misc);
		
		LogHelper.info("Initialized Book of Aura");
	}
	
	public static Tab getTab(int id) {
		return tabMap.get(id);
	}
	
	public static int getID(Tab tab) {
		for(int i = 0; i < tabMap.size(); i++) {
			if(tabMap.get(i) == tab)
				return i;
		}
		
		return -1;
	}
}
