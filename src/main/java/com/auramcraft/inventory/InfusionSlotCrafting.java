package com.auramcraft.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.auramcraft.item.AuraItem;
import com.auramcraft.item.Auras;
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
		if(Minecraft.getMinecraft().theWorld.isRemote)
			LogHelper.info("Serverside");
		
		LogHelper.info("onPickupFromSlot Legacy");
		
		super.onPickupFromSlot(entityPlayer, itemStack);
		
		LogHelper.info("onPickupFromSlot Aura");
		
		AuraItem item;
		
		if(auraItem.getStackInSlot(0) != null)
			item = (AuraItem) auraItem.getStackInSlot(0).getItem();
		else {
			LogHelper.info("There's no item here");
			return;
		}
		
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		for(int i = 0; i < recipes.size(); i++) {
			if(recipes.get(i).getRecipeOutput().getItem().equals(itemStack.getItem())) {
				// Removes auras
				ArrayList auras = new ArrayList(recipes.get(i).getRecipeAuras());
				
				for(int j = 0; j < auras.size(); j = j + 2) {
					Auras aura = (Auras) auras.get(j);
					int amount = (Integer) auras.get(j+1);
					
					item.remove(aura, amount);
				}
				
				auraItem.setInventorySlotContents(0, new ItemStack(item));
				
				LogHelper.info("onPickupFromSlot Aura Done");
				
				return;
			}
		}
	}
}
