package com.auramcraft.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.item.crafting.IInfusionRecipe;
import com.auramcraft.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class InfusionSlotCrafting extends SlotCrafting {
	private SyncedInventory auraItem;
	
	public InfusionSlotCrafting(EntityPlayer entityPlayer, IInventory matrix, IInventory result, SyncedInventory auraItem, int id, int x, int y) {
		super(entityPlayer, matrix, result, id, x, y);
		this.auraItem = auraItem;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
		LogHelper.info("onPickupFromSlot");
		
		// Get container from itemstack
		AuraContainer container = AuraItem.getAuraContainer(auraItem.getStackInSlot(0));
		
		LogHelper.info("Got Container");
		
		// Get infusion recipes
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		LogHelper.info("Got infusion recipes");
		
		for(int i = 0; i < recipes.size(); i++) {
			// if the recipe matches
			if(recipes.get(i).getRecipeOutput().getItem().equals(itemStack.getItem())) {
				LogHelper.info("Recipe matches");
				
				// Get the aura needed by the recipe
				ArrayList auras = new ArrayList(recipes.get(i).getRecipeAuras());
				
				LogHelper.info("Got aura needed");
				
				// Subtract aura cost from container
				for(int j = 0; j < auras.size(); j = j + 2) {
					Auras aura = (Auras) auras.get(j);
					int amount = (Integer) auras.get(j+1);
					
					container.remove(aura, amount);
				}
				
				LogHelper.info("Subtracted aura cost");
				
				// Update container
				AuraItem.updateNBT(auraItem.getStackInSlot(0), container);
				
				LogHelper.info("Updated container");
				
				// No more looping necessary
				break;
			}
		}
		
		LogHelper.info("Calling super");
		
		super.onPickupFromSlot(entityPlayer, itemStack);
		
		if(auraItem.getStackInSlot(0) != null) {
			LogHelper.info("Item is still there");
			auraItem.setInventorySlotContents(0, null);
		}
		else
			LogHelper.info("Item has been picked up");
	}
}
