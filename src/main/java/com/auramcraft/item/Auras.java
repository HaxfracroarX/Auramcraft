package com.auramcraft.item;

import java.util.ArrayList;

public enum Auras {
	// Tier 1
	FIRE(1),
	EARTH(1),
	WATER(1),
	AIR(1),
	AURAM(1),
	
	// Tier 2
	ICE(2, WATER, AURAM),
	LIGHT(2, FIRE, AIR),
	LIFE(2, EARTH, WATER),
	DEATH(2, FIRE, AURAM),
	METAL(2, EARTH, AURAM),
	CLOUD(2, AURAM, AIR),
	SILICA(2, EARTH, FIRE),
	
	// Tier 3
	SPIRIT(3, LIFE, DEATH),
	VACUUM(3, DEATH, AURAM),
	WEATHER(3, CLOUD, WATER),
	HOLY(3, LIGHT, CLOUD),
	ENERGY(3, LIFE, AIR),
	GEM(3, LIGHT, AURAM),
	
	// Tier 4
	MAGIC(4, ENERGY, VACUUM),
	BEAST(4, SPIRIT, LIFE),
	CROP(4, WEATHER, EARTH),
	
	// Tier 5
	KNOWLEDGE(5, BEAST, ENERGY),
	HARVEST(5, CROP, AURAM),
	
	// Tier 6
	HUMAN(6, KNOWLEDGE, BEAST),
	TECH(6, KNOWLEDGE, METAL),
	GREED(6, HARVEST, GEM),
	
	// Tier 7
	GOLEM(7, HUMAN, EARTH),
	TOOL(7, HUMAN, HARVEST),
	SCARE(7, HARVEST, GREED);
	
	private int tier;
	private ArrayList<Auras> ingredients = new ArrayList<Auras>();
	
	Auras(int tier) {
		this.tier = tier;
		ingredients.add(this);
		ingredients.add(this);
	}
	
	Auras(int tier, Auras ingredient1, Auras ingredient2) {
		this.tier = tier;
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
	}
	
	public int getTier() {
		return tier;
	}
	
	public ArrayList<Auras> getIngredients() {
		return ingredients;
	}
}
