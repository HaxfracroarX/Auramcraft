package com.auramcraft.item.crafting;

import java.util.ArrayList;
import java.util.List;
import com.auramcraft.api.Auras;
import com.auramcraft.inventory.InfusionCrafting;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

@SuppressWarnings({"FieldCanBeLocal", "unchecked"})
public class InfusionShapedRecipes implements IInfusionRecipe {
	/** How many horizontal slots this recipe is wide. */
	private final int recipeWidth;
	
	/** How many vertical slots this recipe uses. */
	private final int recipeHeight;
	
	/** List of ItemStack that composes the recipe. */
	private final List recipeItems;
	
	/** List of Auras that compose the recipe */
	private final List recipeAuras;
	
	/** The ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	private final boolean field_92101_f = false;
	
	public InfusionShapedRecipes(int recipeWidth, int recipeHeight, List recipeItems, ItemStack recipeOutput, List recipeAuras) {
		this.recipeWidth = recipeWidth;
		this.recipeHeight = recipeHeight;
		this.recipeItems = recipeItems;
		this.recipeOutput = recipeOutput;
		this.recipeAuras = recipeAuras;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}
	
	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		if(!(inventoryCrafting instanceof InfusionCrafting))
			return false;
		
		InfusionCrafting crafting = (InfusionCrafting) inventoryCrafting;
		ArrayList items = new ArrayList(recipeItems);
		ArrayList auras = new ArrayList(recipeAuras);
		
		// Check for auraItem
		if(crafting.getAuraSlot().getStack() == null)
			return false;
		
		// Check auras
		for(int i = 0; i < auras.size(); i += 2) {
			Auras aura = (Auras) auras.get(i);
			int amount = (Integer) auras.get(i+1);
			
			if(!(crafting.getAuraContainer().getStoredAura(aura) >= amount))
				return false;
		}
		
		// Check 3x3 grid
		for(int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for(int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if(this.checkMatch(crafting, i, j, true))
					return true;
				
				if(this.checkMatch(crafting, i, j, false))
					return true;
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
						itemstack = (ItemStack) this.recipeItems.get(this.recipeWidth - i1 - 1 + j1 * this.recipeWidth);
					}
					else {
						itemstack = (ItemStack) this.recipeItems.get(i1 + j1 * this.recipeWidth);
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

	@Override
	public List getRecipeAuras() {
		return recipeAuras;
	}
}
