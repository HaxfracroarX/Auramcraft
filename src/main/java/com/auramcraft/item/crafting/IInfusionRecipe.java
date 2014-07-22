package com.auramcraft.item.crafting;

import java.util.List;
import net.minecraft.item.crafting.IRecipe;

public interface IInfusionRecipe extends IRecipe {
	public List getRecipeAuras();
}
