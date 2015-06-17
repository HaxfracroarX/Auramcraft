package com.auramcraft.item.crafting;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.item.Wand;
import com.auramcraft.item.WandPart;
import com.auramcraft.reference.Textures;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class InfusionWandShapedRecipe extends InfusionShapedRecipe {
	public InfusionWandShapedRecipe(int recipeWidth, int recipeHeight, List recipeItems, ItemStack recipeOutput, List recipeAuras) {
		super(recipeWidth, recipeHeight, recipeItems, recipeOutput, recipeAuras);
	}
	
	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		if(!super.matches(inventoryCrafting, world))
			return false;
		
		// Get Wand Parts
		ItemStack cap1 = getStack(inventoryCrafting, 0, 3);
		ItemStack core = getStack(inventoryCrafting, 3, 6);
		ItemStack cap2 = getStack(inventoryCrafting, 6, 9);
		
		return (WandPart.getPart(cap1).equals("Cap") &&
			WandPart.getPart(cap2).equals("Cap") &&
			WandPart.getPart(core).equals("Core") &&
			cap1.stackTagCompound.equals(cap2.stackTagCompound));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		// Get Wand Parts
		ItemStack cap = getStack(inventoryCrafting, 0, 3);
		ItemStack core = getStack(inventoryCrafting, 3, 6);
		
		ItemStack result = getRecipeOutput().copy();
		AuraContainer container = new AuraContainer(WandPart.getMaxAura(cap), WandPart.getTier(core));
		
		Wand.updateNBT(result, container, WandPart.getTextureID(core), WandPart.getTextureID(cap), Textures.Items.WAND_EMPTY);
		
		return result;
	}
	
	private ItemStack getStack(InventoryCrafting inventoryCrafting, int start, int stop) {
		ItemStack stack = null;
		for(int i = start; i < stop; i++) {
			ItemStack itemStack = inventoryCrafting.getStackInSlot(i);
			
			if(itemStack != null)
				stack = itemStack.copy();
		}
		return stack;
	}
}
