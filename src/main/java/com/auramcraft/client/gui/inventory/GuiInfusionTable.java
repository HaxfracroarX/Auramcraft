package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.Auramcraft;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.reference.Reference;
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
		xSize = 192;
		ySize = 96;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "/textures/gui/infusion/infusionTable.png"));
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
