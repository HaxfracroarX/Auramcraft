package com.auramcraft.item;

import java.util.ArrayList;

public enum Auras {
	// Tier 1
	FIRE(1, 0),
	EARTH(1, 1),
	WATER(1, 2),
	AIR(1, 3),
	AURAM(1, 4),
	
	// Tier 2
	ICE(2, 0, WATER, AURAM),
	LIGHT(2, 1, FIRE, AIR),
	LIFE(2, 2, EARTH, WATER),
	DEATH(2, 3, FIRE, AURAM),
	METAL(2, 4, EARTH, AURAM),
	CLOUD(2, 5, AURAM, AIR),
	SILICA(2, 6, EARTH, FIRE),
	
	// Tier 3
	SPIRIT(3, 0, LIFE, DEATH),
	VACUUM(3, 1, DEATH, AURAM),
	WEATHER(3, 2, CLOUD, WATER),
	HOLY(3, 3, LIGHT, CLOUD),
	ENERGY(3, 4, LIFE, AIR),
	GEM(3, 5, LIGHT, AURAM),
	
	// Tier 4
	MAGIC(4, 0, ENERGY, VACUUM),
	BEAST(4, 1, SPIRIT, LIFE),
	CROP(4, 2, WEATHER, EARTH),
	
	// Tier 5
	KNOWLEDGE(5, 0, BEAST, ENERGY),
	HARVEST(5, 1, CROP, AURAM),
	
	// Tier 6
	HUMAN(6, 0, KNOWLEDGE, BEAST),
	TECH(6, 1, KNOWLEDGE, METAL),
	GREED(6, 2, HARVEST, GEM),
	
	// Tier 7
	GOLEM(7, 0, HUMAN, EARTH),
	TOOL(7, 1, HUMAN, HARVEST),
	SCARE(7, 2, HARVEST, GREED);
	
	private int tier, id;
	private ArrayList<Auras> ingredients = new ArrayList<Auras>();
	
	Auras(int tier, int id) {
		setup(tier, id, this, this);
	}
	
	Auras(int tier, int id, Auras ingredient1, Auras ingredient2) {
		setup(tier, id, ingredient1, ingredient2);
	}
	
	private void setup(int tier, int id, Auras ingredient1, Auras ingredient2) {
		this.tier = tier;
		this.id = id;
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
	}
	
	public int getTier() {
		return tier;
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Auras> getIngredients() {
		return ingredients;
	}
}
