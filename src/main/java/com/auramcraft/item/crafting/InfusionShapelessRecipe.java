package com.auramcraft.item.crafting;

import com.auramcraft.api.Auras;
import com.auramcraft.inventory.InfusionCrafting;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class InfusionShapelessRecipe implements IInfusionRecipe {
	/** The ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	
	/** List of ItemStack that composes the recipe. */
	private final List recipeItems;
	
	/** List of Auras that compose the recipe */
	private final List recipeAuras;
	
	public InfusionShapelessRecipe(ItemStack recipeOutput, List recipeItems, List recipeAuras) {
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
					
					for(Object item : items) {
						ItemStack itemstack1 = (ItemStack) item;
						
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
			
			if(!(crafting.getAuraSlot().getItemAuraContainer().getStoredAura(aura) >= amount))
				return false;
		}
		
		return items.isEmpty();
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		return getRecipeOutput().copy();
	}
	
	@Override
	public int getRecipeSize() {
		return recipeItems.size();
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}
	
	protected List getRecipeItems() {
		return recipeItems;
	}
	
	@Override
	public List getRecipeAuras() {
		return recipeAuras;
	}
}
