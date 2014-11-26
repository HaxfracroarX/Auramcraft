package com.auramcraft.item.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.inventory.InfusionCrafting;
import com.auramcraft.item.AuraItem;
import com.auramcraft.util.LogHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class InfusionShapelessRecipes implements IInfusionRecipe {
	/** The ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	
	/** List of ItemStack that composes the recipe. */
	public final List recipeItems;
	
	/** List of Auras that compose the recipe */
	public final List recipeAuras;
	
	public InfusionShapelessRecipes(ItemStack recipeOutput, List recipeItems, List recipeAuras) {
		this.recipeOutput = recipeOutput;
		this.recipeItems = recipeItems;
		this.recipeAuras = recipeAuras;
	}
	
	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		if(!(inventoryCrafting instanceof InfusionCrafting))
			return false;
		
		InfusionCrafting crafting = (InfusionCrafting) inventoryCrafting;
		ArrayList items = new ArrayList(recipeItems);
		ArrayList auras = new ArrayList(recipeAuras);
		
		// Check for AuraItem
		if(crafting.getAuraSlot().getStack() == null)
			return false;
		
		// Check 3x3 grid
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				ItemStack itemstack = crafting.getStackInRowAndColumn(j, i);
				
				if(itemstack != null) {
					boolean flag = false;
					Iterator iterator = items.iterator();
					
					while(iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack) iterator.next();
						
						if(itemstack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemstack.getItemDamage() == itemstack1.getItemDamage())) {
							flag = true;
							items.remove(itemstack1);
							break;
						}
					}
					
					if(!flag)
						return false;
				}
			}
		}
		
		// Check auras
		for(int i = 0; i < auras.size(); i += 2) {
			Auras aura = (Auras) auras.get(i);
			int amount = (Integer) auras.get(i+1);
			
			if(!(crafting.getAuraContainer().getStoredAura(aura) >= amount))
				return false;
		}
		
		return items.isEmpty();
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return recipeOutput.copy();
	}
	
	@Override
	public int getRecipeSize() {
		return recipeItems.size();
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}
	
	@Override
	public List getRecipeAuras() {
		return recipeAuras;
	}
}
