package com.auramcraft.item.crafting;

import net.minecraft.item.crafting.IRecipe;

import java.util.Comparator;

public class AuramcraftRecipeSorter implements Comparator {
	private final AuramcraftCraftingManager craftingManager;
	
	public AuramcraftRecipeSorter(AuramcraftCraftingManager craftingManager) {
		this.craftingManager = craftingManager;
	}
	
	public int compareRecipes(IRecipe recipe1, IRecipe recipe2)
	{
		 return recipe1 instanceof InfusionShapelessRecipe && recipe2 instanceof InfusionShapedRecipe ? 1 : (recipe2 instanceof InfusionShapelessRecipe && recipe1 instanceof InfusionShapedRecipe ? -1 : (recipe2.getRecipeSize() < recipe1.getRecipeSize() ? -1 : (recipe2.getRecipeSize() > recipe1.getRecipeSize() ? 1 : 0)));
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
