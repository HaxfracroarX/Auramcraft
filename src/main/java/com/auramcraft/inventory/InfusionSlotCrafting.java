package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.item.crafting.IInfusionRecipe;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"unchecked", "SameParameterValue", "WeakerAccess"})
public class InfusionSlotCrafting extends Slot {
	private final InfusionCrafting matrix;
	private final Container eventhandler;
	
	public InfusionSlotCrafting(TileInfusionTable tileEntity, int id, int x, int y, InfusionCrafting matrix, Container eventhandler) {
		super(tileEntity, id, x, y);
		this.matrix = matrix;
		this.eventhandler = eventhandler;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
		// Get container from itemstack
		AuraContainer container = AuraItem.getAuraContainer(matrix.getStackInSlot(9));
		
		// Get infusion recipes
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		// Flag for recipes
		boolean foundRecipe = false;
		
		for(IInfusionRecipe recipe : recipes) {
			// Get the aura needed by the recipe
			ArrayList auras = new ArrayList(recipe.getRecipeAuras());
			
			// If the recipe matches
			if(recipe.getRecipeOutput().getItem().equals(itemStack.getItem()) && container.containsAmount(auras)) { // TODO: Check if it found a recipe or not
				// Subtract aura cost from container
				for(int j = 0; j < auras.size(); j += 2) {
					Auras aura = (Auras) auras.get(j);
					int amount = (Integer) auras.get(j + 1);
					
					// Remove auras used in recipe
					container.remove(aura, amount);
				}
				
				// Update container
				AuraItem.updateNBT(matrix.getStackInSlot(9), container);
				
				// Check off flag
				foundRecipe = true;
				
				// No more looping necessary
				break;
			}
		}
		
		// Check if the input matched any recipes.
		if(!foundRecipe)
			return;
		
		// Deletes the items used in the recipe
		for(int i = 0; i < 9; i++)
			matrix.decrStackSize(i, 1);
		
		// Refresh output slot
		eventhandler.onCraftMatrixChanged(null);
		
		super.onPickupFromSlot(entityPlayer, itemStack);
		
		// Refresh output slot
		eventhandler.onCraftMatrixChanged(null);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		return false;
	}
}
