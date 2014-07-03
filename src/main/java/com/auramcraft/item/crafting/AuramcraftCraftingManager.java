package com.auramcraft.item.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class AuramcraftCraftingManager {
	private static final AuramcraftCraftingManager instance = new AuramcraftCraftingManager();
	private List recipes = new ArrayList();
	
	private AuramcraftCraftingManager() {
		/*	SHAPED	*/
		// Book of Aura - Testing
		addRecipe(new ItemStack(AuramcraftItems.bookOfAura), new Object[] {
			" 1 ",
			"234",
			" 5 ",
			'1', AuramcraftItems.airShard,
			'2', AuramcraftItems.fireShard,
			'3', AuramcraftItems.earthShard,
			'4', AuramcraftItems.waterShard,
			'5', AuramcraftItems.auramShard
		});
		
		/*	SHAPELESS	*/
		// TODO: Shapeless recipes
		
		Collections.sort(recipes, new AuramcraftRecipeSorter(this));
		
		LogHelper.info("Infusion Recipes Initialized");
	}
	
	public static final AuramcraftCraftingManager getInstance() {
		return instance;
	}
	
	public ShapedRecipes addRecipe(ItemStack itemStack, Object... objects) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		
		if(objects[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) objects[i++]);
			
			for(int l = 0; l < astring.length; ++l) {
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else {
			while(objects[i] instanceof String) {
				String s2 = (String) objects[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		
		HashMap hashmap;
		
		for(hashmap = new HashMap(); i < objects.length; i += 2) {
			Character character = (Character) objects[i];
			ItemStack itemstack1 = null;
			
			if(objects[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) objects[i + 1]);
			}
			else if(objects[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) objects[i + 1], 1, 32767);
			}
			else if(objects[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) objects[i + 1];
			}
			
			hashmap.put(character, itemstack1);
		}
		
		ItemStack[] aitemstack = new ItemStack[j * k];
		
		for(int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);
			
			if(hashmap.containsKey(Character.valueOf(c0))) {
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			}
			else {
				aitemstack[i1] = null;
			}
		}
		
		ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, itemStack);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}
	
	public void addShapelessRecipe(ItemStack itemStack, Object... objects) {
		ArrayList arraylist = new ArrayList();
		Object[] aobject = objects;
		int i = objects.length;
		
		for(int j = 0; j < i; ++j) {
			Object object1 = aobject[j];
			
			if(object1 instanceof ItemStack) {
				arraylist.add(((ItemStack) object1).copy());
			}
			else if(object1 instanceof Item) {
				arraylist.add(new ItemStack((Item) object1));
			}
			else {
				if(!(object1 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipy!");
				}
				
				arraylist.add(new ItemStack((Block) object1));
			}
		}
		
		this.recipes.add(new ShapelessRecipes(itemStack, arraylist));
	}
	
	public ItemStack findMatchingRecipe(InventoryCrafting inventoryCrafting, World world) {
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		int j;
		
		for(j = 0; j < inventoryCrafting.getSizeInventory(); ++j) {
			ItemStack itemstack2 = inventoryCrafting.getStackInSlot(j);
			
			if(itemstack2 != null) {
				if(i == 0) {
					itemstack = itemstack2;
				}
				
				if(i == 1) {
					itemstack1 = itemstack2;
				}
				
				++i;
			}
		}
		
		if(i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable()) {
			Item item = itemstack.getItem();
			int j1 = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
			int k = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
			int l = j1 + k + item.getMaxDamage() * 5 / 100;
			int i1 = item.getMaxDamage() - l;
			
			if(i1 < 0) {
				i1 = 0;
			}
			
			return new ItemStack(itemstack.getItem(), 1, i1);
		}
		else {
			for(j = 0; j < this.recipes.size(); ++j) {
				IRecipe irecipe = (IRecipe) this.recipes.get(j);
				
				if(irecipe.matches(inventoryCrafting, world)) {
					return irecipe.getCraftingResult(inventoryCrafting);
				}
			}
			
			return null;
		}
	}
	
	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}
