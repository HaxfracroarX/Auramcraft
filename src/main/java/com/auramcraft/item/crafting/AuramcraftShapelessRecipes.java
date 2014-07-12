package com.auramcraft.item.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.auramcraft.item.IAuraContainer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class AuramcraftShapelessRecipes implements IRecipe {
	/** The ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	
	/** List of ItemStack that composes the recipe. */
	public final List recipeItems;
	
	public AuramcraftShapelessRecipes(ItemStack recipeOutput, List recipeItems) {
		this.recipeOutput = recipeOutput;
		this.recipeItems = recipeItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		if(!interfacesIAuraContainer(inventoryCrafting))
			return false;
		
		ArrayList arraylist = new ArrayList(this.recipeItems);
		
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				ItemStack itemstack = inventoryCrafting.getStackInRowAndColumn(j, i);
				
				if(itemstack != null) {
					boolean flag = false;
					Iterator iterator = arraylist.iterator();
					
					while(iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack) iterator.next();
						
						if(itemstack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemstack.getItemDamage() == itemstack1.getItemDamage())) {
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}
					
					if(!flag) {
						return false;
					}
				}
			}
		}
		
		return arraylist.isEmpty();
	}
	
	public boolean interfacesIAuraContainer(Object object) {
		Class[] classes = object.getClass().getInterfaces();
		
		for(int i = 0; i < classes.length; i++) {
			if(classes[i].isAssignableFrom(IAuraContainer.class))
				return true;
		}
		
		return false;
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
}
