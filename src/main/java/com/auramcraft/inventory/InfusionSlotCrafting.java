package com.auramcraft.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.item.crafting.IInfusionRecipe;
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
	private TileInfusionTable tileEntity;
	
	public InfusionSlotCrafting(TileInfusionTable tileEntity, int id, int x, int y) {
		super(tileEntity, id, x, y);
		this.tileEntity = tileEntity;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
		// Get container from itemstack
		AuraContainer container = AuraItem.getAuraContainer(tileEntity.getStackInSlot(9));
		
		// Get infusion recipes
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		for(int i = 0; i < recipes.size(); i++) {
			// if the recipe matches
			if(recipes.get(i).getRecipeOutput().getItem().equals(itemStack.getItem())) {
				
				// Get the aura needed by the recipe
				ArrayList auras = new ArrayList(recipes.get(i).getRecipeAuras());
				
				// Subtract aura cost from container
				for(int j = 0; j < auras.size(); j = j + 2) {
					Auras aura = (Auras) auras.get(j);
					int amount = (Integer) auras.get(j+1);
					
					// If it can't remove the aura needed
					if(!container.remove(aura, amount))
						return;
				}
				
				// Update container
				AuraItem.updateNBT(tileEntity.getStackInSlot(9), container);
				
				// No more looping necessary
				break;
			}
		}
		
		super.onPickupFromSlot(entityPlayer, itemStack);
	}
}
