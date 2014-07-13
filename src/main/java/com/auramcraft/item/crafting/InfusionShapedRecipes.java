package com.auramcraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class InfusionShapedRecipes implements IRecipe {
	/** How many horizontal slots this recipe is wide. */
	public final int recipeWidth;
	/** How many vertical slots this recipe uses. */
	public final int recipeHeight;
	/** array of ItemStack that composes the recipe. */
	public final ItemStack[] recipeItems;
	/** The ItemStack that you get when craft the recipe. */
	private ItemStack recipeOutput;
	private boolean field_92101_f = false;
	
	public InfusionShapedRecipes(int recipeWidth, int recipeHeight, ItemStack[] recipeItems, ItemStack recipeOutput) {
		this.recipeWidth = recipeWidth;
		this.recipeHeight = recipeHeight;
		this.recipeItems = recipeItems;
		this.recipeOutput = recipeOutput;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}
	
	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		for(int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for(int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if(this.checkMatch(inventoryCrafting, i, j, true)) {
					return true;
				}
				
				if(this.checkMatch(inventoryCrafting, i, j, false)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean checkMatch(InventoryCrafting inventoryCrafting, int x, int y, boolean par4) {
		for(int k = 0; k < 3; ++k) {
			for(int l = 0; l < 3; ++l) {
				int i1 = k - x;
				int j1 = l - y;
				ItemStack itemstack = null;
				
				if(i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
					if(par4) {
						itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
					}
					else {
						itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}
				
				ItemStack itemstack1 = inventoryCrafting.getStackInRowAndColumn(k, l);
				
				if(itemstack1 != null || itemstack != null) {
					if(itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
						return false;
					}
					
					if(itemstack.getItem() != itemstack1.getItem()) {
						return false;
					}
					
					if(itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		ItemStack itemstack = this.getRecipeOutput().copy();

		 if (field_92101_f)
		 {
			 for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i)
			 {
				 ItemStack itemstack1 = inventoryCrafting.getStackInSlot(i);

				 if (itemstack1 != null && itemstack1.hasTagCompound())
				 {
					 itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
				 }
			 }
		 }

		 return itemstack;
	}
	
	@Override
	public int getRecipeSize() {
		return recipeWidth * recipeHeight;
	}

	public InfusionShapedRecipes func_92100_c() {
		 this.field_92101_f = true;
		 return this;
	}
}
