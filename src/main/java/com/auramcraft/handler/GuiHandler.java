package com.auramcraft.handler;

import com.auramcraft.client.gui.inventory.GuiInfusionTable;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0) {
			TileEntityInfusionTable tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
			return new ContainerInfusionTable(player.inventory, tileEntityInfusionTable);
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0) {
			TileEntityInfusionTable tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
			return new GuiInfusionTable(player.inventory, tileEntityInfusionTable);
		}
		return null;
	}
}
