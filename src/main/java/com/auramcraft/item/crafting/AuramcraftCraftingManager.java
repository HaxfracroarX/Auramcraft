package com.auramcraft.item.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.inventory.InfusionCrafting;
import com.auramcraft.item.Auras;
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
		/* SHAPED */
		
		/* SHAPELESS */
		// Fire Shard
		addShapelessRecipe(new ItemStack(AuramcraftItems.fireShard), new Object[] {
			AuramcraftItems.gemstone
		}, new Object[] {
			Auras.FIRE, 5
		});
		
		Collections.sort(recipes, new AuramcraftRecipeSorter(this));
		
		LogHelper.info("Infusion Recipes Initialized");
	}
	
	public static final AuramcraftCraftingManager getInstance() {
		return instance;
	}
	
	public InfusionShapedRecipes addRecipe(ItemStack itemStack, Object... objects) {
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
		
		InfusionShapedRecipes shapedrecipes = new InfusionShapedRecipes(j, k, aitemstack, itemStack);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}
	
	public void addShapelessRecipe(ItemStack itemStack, Object[] items, Object[] auras) {
		ArrayList itemList = new ArrayList();
		ArrayList auraList = new ArrayList();
		
		for(int i = 0; i < items.length; ++i) {
			Object item = items[i];
			
			if(item instanceof ItemStack)
				itemList.add(((ItemStack) item).copy());
			else if(item instanceof Item)
				itemList.add(new ItemStack((Item) item));
			else {
				if(!(item instanceof Block))
					throw new RuntimeException("Invalid shapeless recipe!");
				
				itemList.add(new ItemStack((Block) item));
			}
		}
		
		for(Object aura : auras)
			auraList.add(aura);
		
		this.recipes.add(new InfusionShapelessRecipes(itemStack, itemList, auraList));
	}
	
	public ItemStack findMatchingRecipe(InfusionCrafting crafting, World world) {
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		int j;
		
		for(j = 0; j < crafting.getSizeInventory(); ++j) {
			ItemStack itemstack2 = crafting.getStackInSlot(j);
			
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
				
				if(irecipe.matches(crafting, world)) {
					return irecipe.getCraftingResult(crafting);
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
