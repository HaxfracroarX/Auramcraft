package com.auramcraft.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.item.crafting.IInfusionRecipe;
import com.auramcraft.item.crafting.InfusionShapedRecipes;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class InfusionSlotCrafting extends Slot {
	private InfusionCrafting matrix;
	
	public InfusionSlotCrafting(TileInfusionTable tileEntity, int id, int x, int y, InfusionCrafting matrix) {
		super(tileEntity, id, x, y);
		this.matrix = matrix;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
		// Get container from itemstack
		AuraContainer container = AuraItem.getAuraContainer(matrix.getStackInSlot(9));
		
		// Get infusion recipes
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		for(int i = 0; i < recipes.size(); i++) {
			// Get the aura needed by the recipe
			ArrayList auras = new ArrayList(recipes.get(i).getRecipeAuras());
			
			// If the recipe matches
			if(recipes.get(i).getRecipeOutput().getItem().equals(itemStack.getItem()) && container.containsAmount(auras)) {
				// Subtract aura cost from container
				for(int j = 0; j < auras.size(); j += 2) {
					Auras aura = (Auras) auras.get(j);
					int amount = (Integer) auras.get(j+1);
					
					// Remove auras used in recipe
					container.remove(aura, amount);
				}
				
				// Update container
				AuraItem.updateNBT(matrix.getStackInSlot(9), container);
				
				// No more looping necessary
				break;
			}
		}
		
		// Deletes the items used in the recipe
		for(int i = 0; i < 9; i++)
			matrix.decrStackSize(i, 1);
		
		super.onPickupFromSlot(entityPlayer, itemStack);
	}
	
	@Override
	public void onSlotChanged() {
		LogHelper.info("Output changed: " + (getStack() == null ? "null" : getStack().getDisplayName()));
		super.onSlotChanged();
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		return false;
	}
}
