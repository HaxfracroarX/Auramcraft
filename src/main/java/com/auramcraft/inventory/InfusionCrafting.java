package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.tileentity.TileAuramcraft;
import com.auramcraft.tileentity.TileAuramcraftInventory;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InfusionCrafting extends InventoryCrafting implements IAuraUser {
	private Container eventHandler;
	private AuraContainer auraContainer;
	private TileInfusionTable tileEntity;
	private AuraSlot auraSlot;
	
	public InfusionCrafting(Container container, int width, int height, int maxAura, int tier, TileInfusionTable tileEntity, AuraSlot auraSlot) {
		super(container, width, height);
		this.eventHandler = container;
		this.tileEntity = tileEntity;
		this.auraSlot = auraSlot;
		auraContainer = new AuraContainer(maxAura, tier);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				setInventorySlotContents(j + i * 3, tileEntity.getStackInSlot(j + i * 3));
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
                eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            else {
                itemstack = tileEntity.getStackInSlot(p_70298_1_).splitStack(p_70298_2_);
                
                if (tileEntity.getStackInSlot(p_70298_1_).stackSize == 0)
                    tileEntity.setInventorySlotContents(p_70298_1_, null);

                eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
        }
        else
            return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		tileEntity.setInventorySlotContents(slot, itemStack);
		eventHandler.onCraftMatrixChanged(this);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	@Override
	public AuraContainer getAuraContainer() {
		return auraContainer;
	}
	
	public AuraSlot getAuraSlot() {
		return auraSlot;
	}
	
	@Override
	public void setAuraContainer(AuraContainer auraContainer) {
		this.auraContainer = auraContainer;
	}
}
