package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

@SuppressWarnings("SameParameterValue")
public class InfusionCrafting extends InventoryCrafting {
	private final Container eventHandler;
	private final TileInfusionTable tileEntity;
	private final AuraSlot auraSlot;
	
	public InfusionCrafting(Container container, int width, int height, TileInfusionTable tileEntity, AuraSlot auraSlot) {
		super(container, width, height);
		this.eventHandler = container;
		this.tileEntity = tileEntity;
		this.auraSlot = auraSlot;
	}

	public void init() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				super.setInventorySlotContents(j + i * 3, tileEntity.getStackInSlot(j + i * 3));
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return tileEntity.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		if (tileEntity.getStackInSlot(p_70298_1_) != null) {
            ItemStack itemstack;

            if (tileEntity.getStackInSlot(p_70298_1_).stackSize <= p_70298_2_) {
                itemstack = tileEntity.getStackInSlot(p_70298_1_);
                tileEntity.setInventorySlotContents(p_70298_1_, null);
                eventHandler.onCraftMatrixChanged(null);
                return itemstack;
            }
            else {
                itemstack = tileEntity.getStackInSlot(p_70298_1_).splitStack(p_70298_2_);
                
                if (tileEntity.getStackInSlot(p_70298_1_).stackSize == 0)
                    tileEntity.setInventorySlotContents(p_70298_1_, null);

                eventHandler.onCraftMatrixChanged(null);
                return itemstack;
            }
        }
        else
            return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		tileEntity.setInventorySlotContents(slot, itemStack);
		eventHandler.onCraftMatrixChanged(null);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	public AuraSlot getAuraSlot() {
		return auraSlot;
	}
}
