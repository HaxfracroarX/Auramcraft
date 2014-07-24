package com.auramcraft.tileentity;

import com.auramcraft.Auramcraft;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityInfusionTable extends TileEntityAuramcraftInventory {
	public TileEntityInfusionTable() {
		super("Infusion Table", AuramcraftBlocks.infusionTable, 11);
	}
}
