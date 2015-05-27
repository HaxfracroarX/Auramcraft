package com.auramcraft.item.crafting;

import java.util.Comparator;
import net.minecraft.item.crafting.IRecipe;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal", "ComparatorMethodParameterNotUsed"})
public class AuramcraftRecipeSorter implements Comparator {
	private final AuramcraftCraftingManager craftingManager;
	
	public AuramcraftRecipeSorter(AuramcraftCraftingManager craftingManager) {
		this.craftingManager = craftingManager;
	}
	
	public int compareRecipes(IRecipe recipe1, IRecipe recipe2)
	{
		 return recipe1 instanceof InfusionShapelessRecipes && recipe2 instanceof InfusionShapedRecipes ? 1 : (recipe2 instanceof InfusionShapelessRecipes && recipe1 instanceof InfusionShapedRecipes ? -1 : (recipe2.getRecipeSize() < recipe1.getRecipeSize() ? -1 : (recipe2.getRecipeSize() > recipe1.getRecipeSize() ? 1 : 0)));
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
