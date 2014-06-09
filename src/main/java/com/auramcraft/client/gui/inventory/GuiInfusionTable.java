package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.Auramcraft;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiInfusionTable extends GuiContainer {
	private TileEntityInfusionTable tileEntityInfusionTable;
	
	public GuiInfusionTable(InventoryPlayer inventoryPlayer, TileEntityInfusionTable tileEntityInfusionTable) {
		super(new ContainerInfusionTable(inventoryPlayer, tileEntityInfusionTable));
		this.tileEntityInfusionTable = tileEntityInfusionTable;
		
		// Will fix when texture is ready
		xSize = 100;
		ySize = 100;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(new ResourceLocation(Auramcraft.MODID, "/gui/infusion/infusionTable.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) /2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
