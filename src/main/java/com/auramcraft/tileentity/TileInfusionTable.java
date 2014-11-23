package com.auramcraft.tileentity;

import com.auramcraft.Auramcraft;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.inventory.InfusionCrafting;
import com.auramcraft.item.AuraItem;
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
}
