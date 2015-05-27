package com.auramcraft.handler;

import com.auramcraft.client.gui.inventory.GuiBookOfAura;
import com.auramcraft.client.gui.inventory.GuiInfusionTable;
import com.auramcraft.inventory.ContainerEmpty;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.reference.GUIIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case GUIIds.INFUSION_TABLE:
				return new ContainerInfusionTable(player.inventory, world, x, y, z);
			case GUIIds.BOOK_OF_AURA:
				return new ContainerEmpty();
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case GUIIds.INFUSION_TABLE:
				return new GuiInfusionTable(player.inventory, world, x, y, z);
			case GUIIds.BOOK_OF_AURA:
				return new GuiBookOfAura();
		}
		return null;
	}
}
