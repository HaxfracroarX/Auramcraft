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
import net.minecraft.world.World;

import java.util.ArrayList;

@SuppressWarnings({"unchecked", "SameParameterValue", "WeakerAccess"})
public class InfusionSlotCrafting extends Slot {
	private final InfusionCrafting matrix;
	private final Container eventHandler;
	private World world;
	
	public InfusionSlotCrafting(TileInfusionTable tileEntity, int id, int x, int y, InfusionCrafting matrix, Container eventHandler) {
		super(tileEntity, id, x, y);
		this.matrix = matrix;
		this.eventHandler = eventHandler;
		this.world = tileEntity.getWorldObj();
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
		// Get container from itemStack
		AuraContainer container = AuraItem.getAuraContainer(matrix.getStackInSlot(9));
		
		// Get infusion recipe
		IInfusionRecipe recipe = AuramcraftCraftingManager.getInstance().findMatchingRecipe(matrix, world);
	
		// Get the aura needed by the recipe
		ArrayList auras = new ArrayList(recipe.getRecipeAuras());
		
		// If the container contains the cost
		if(container.containsAmount(auras)) {
			// Subtract aura cost from container
			for(int j = 0; j < auras.size(); j += 2) {
				Auras aura = (Auras) auras.get(j);
				int amount = (Integer) auras.get(j + 1);
				
				// Remove auras used in recipe
				container.remove(aura, amount);
			}
			
			// Update container
			AuraItem.updateNBT(matrix.getStackInSlot(9), container);
			
			// Delete the items used in the recipe
			for(int i = 0; i < 9; i++)
				matrix.decrStackSize(i, 1);
			
			// Refresh output slot
			eventHandler.onCraftMatrixChanged(null);
		}
		
		super.onPickupFromSlot(entityPlayer, itemStack);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		return false;
	}
}
