package com.auramcraft.item;

import java.util.ArrayList;

public abstract class Aura {
	private String name;
	private Auras type;
	
	public Aura(String name, Auras type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTier() {
		return type.getTier();
	}
	
	public abstract ArrayList<Auras> getIngredients();
}
