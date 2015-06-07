package com.auramcraft.client.gui.inventory;


import java.util.ArrayList;
import java.util.Arrays;

public class Tab {
	private ArrayList<BookPage> pages;
	private String name;
	
	public Tab(String name, BookPage... pages) {
		this.name = name;
		this.pages = new ArrayList<BookPage>(Arrays.asList(pages));
	}
	
	public ArrayList<BookPage> getPages() {
		return pages;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
