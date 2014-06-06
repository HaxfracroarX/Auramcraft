package com.auramcraft.item;

public enum Auras {
	// Tier 1
	FIRE(1),
	EARTH(1),
	WATER(1),
	AIR(1),
	AURAM(1),
	
	// Tier 2
	ICE(2),
	LIGHT(2),
	LIFE(2),
	DEATH(2),
	METAL(2),
	CLOUD(2),
	
	// Tier 3
	SPIRIT(3),
	VACUMM(3),
	WEATHER(3),
	HOLY(3),
	ENERGY(3),
	GEM(3),
	
	// Tier 4
	MAGIC(4),
	BEAST(4),
	CROP(4),
	
	// Tier 5
	KNOWLEDGE(5),
	HARVEST(5),
	
	// Tier 6
	HUMAN(6),
	TECH(6),
	GREED(6),
	
	// Tier 7
	GOLEM(7),
	TOOL(7),
	SCARE(7);
	
	private int tier;
	
	Auras(int tier) {
		this.tier = tier;
	}
	
	public int getTier() {
		return tier;
	}
}
