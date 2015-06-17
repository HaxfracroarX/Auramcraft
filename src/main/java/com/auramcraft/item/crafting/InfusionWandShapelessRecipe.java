package com.auramcraft.item.crafting;

import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.item.Wand;
import com.auramcraft.item.WandPart;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InfusionWandShapelessRecipe extends InfusionShapelessRecipe {
	public InfusionWandShapelessRecipe(ItemStack recipeOutput, List recipeItems, List recipeAuras) {
		super(recipeOutput, recipeItems, recipeAuras);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		ItemStack itemStack = getRecipeOutput().copy();
		
		// Move over Wand data to output
		String cloth = "";
		int i;
		for(i = 0; i < getRecipeItems().size(); i++) {
			ItemStack check = (ItemStack) getRecipeItems().get(i);
			
			if(check == null)
				continue;
			
			cloth = WandPart.getPart(check);
			
			if(cloth.equals("Cloth"))
				break;
		}
		
		if(itemStack.getItem() == AuramcraftItems.wand && cloth.equals("Cloth")) {
			for(int j = 0; j < inventoryCrafting.getSizeInventory(); ++j) {
				ItemStack itemStack1 = inventoryCrafting.getStackInSlot(j);
				
				if(itemStack1 != null && itemStack1.getItem() == AuramcraftItems.wand)
					Wand.updateNBT(
						itemStack, Wand.getAuraContainer(itemStack1),
						Wand.getCore(itemStack1), Wand.getCap(itemStack1),
						WandPart.getTextureID((ItemStack) getRecipeItems().get(i))
					);
			}
		}
		
		return itemStack;
	}
}
