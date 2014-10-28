package com.auramcraft.tileentity;

import com.auramcraft.Auramcraft;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileInfusionTable extends TileAuramcraftInventory {
	public TileInfusionTable() {
		super("Infusion Table", AuramcraftBlocks.infusionTable, 11);
	}
	
	public InventoryCrafting getCraftingMatrix(Container container) {
		InventoryCrafting matrix = new InventoryCrafting(container, 3, 3);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				matrix.setInventorySlotContents(j + i * 3, getStackInSlot(j + i * 3));
		}
		
		return matrix;
	}
}
