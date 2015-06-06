package com.auramcraft.client.gui.inventory;

import scala.actors.threadpool.Arrays;

import java.util.ArrayList;

public class Tab {
	private ArrayList<BookPage> pages;
	
	public Tab(BookPage... pages) {
		this.pages = new ArrayList<BookPage>(Arrays.asList(pages));
	}
	
	public ArrayList<BookPage> getPages() {
		return pages;
	}
}
