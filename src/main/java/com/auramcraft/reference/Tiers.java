package com.auramcraft.reference;

public class Tiers {
	public static final int[] tiers = new int[] {
		5, 7, 6, 3, 2, 3, 3
	};
	
	public static int getTotalAuras(int tier) {
		return tiers[tier];
	}
}
