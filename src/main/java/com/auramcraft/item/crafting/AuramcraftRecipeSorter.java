package com.auramcraft.item.crafting;

import java.util.Comparator;
import net.minecraft.item.crafting.IRecipe;

public class AuramcraftRecipeSorter implements Comparator {
	final AuramcraftCraftingManager craftingManager;
	
	public AuramcraftRecipeSorter(AuramcraftCraftingManager craftingManager) {
		this.craftingManager = craftingManager;
	}
	
	public int compareRecipes(IRecipe recipe1, IRecipe recipe2)
	{
		 return recipe1 instanceof AuramcraftShapelessRecipes && recipe2 instanceof AuramcraftShapedRecipes ? 1 : (recipe2 instanceof AuramcraftShapelessRecipes && recipe1 instanceof AuramcraftShapedRecipes ? -1 : (recipe2.getRecipeSize() < recipe1.getRecipeSize() ? -1 : (recipe2.getRecipeSize() > recipe1.getRecipeSize() ? 1 : 0)));
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
