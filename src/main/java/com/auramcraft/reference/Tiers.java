package com.auramcraft.reference;

public class Tiers {
	public static final int[] tierSize = new int[] {
		5, 7, 6, 3, 2, 3, 3
	};
	
	public static int getTotalAuras(int tier) {
		return tierSize[tier];
	}
	
	public static int getTotalTiers() {
		return tierSize.length;
	}
}
