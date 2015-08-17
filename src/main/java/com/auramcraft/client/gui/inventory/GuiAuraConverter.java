package com.auramcraft.client.gui.inventory;

import com.auramcraft.inventory.ContainerAuraConverter;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileAuraConverter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiAuraConverter extends GuiContainer {
	private TileAuraConverter tileEntity;
	private final int 
		arrowTemplateX = 244,
		arrowTemplateY = 0,
		arrow1X = 70,
		arrow1Y = 38,
		arrow2X = 108,
		arrow2Y = 38;
	
	public GuiAuraConverter(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(new ContainerAuraConverter(inventoryPlayer, world, x, y, z));
		tileEntity = (TileAuraConverter) world.getTileEntity(x, y, z);
		xSize = 191;
		ySize = 190;
	}
	
	/**
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mx, int my) {
		mc.getTextureManager().bindTexture(Textures.GUI.AURA_CONVERTER);
		
		if(tileEntity.isConverting()) {
			drawTexturedModalRect(arrow1X, arrow1Y, arrowTemplateX, arrowTemplateY, (int) (12f * (((float) tileEntity.getTickCounter()) / 40f)), 10);
			drawTexturedModalRect(arrow2X, arrow2Y, arrowTemplateX, arrowTemplateY, (int) (12f * (((float) tileEntity.getTickCounter()) / 40f)), 10);
			drawTexturedModalRect(87, 34, 240, 11 + (16 * tileEntity.getOutput().getId()), 16, 16);
		}
	}
	
	/**
	 * @param renderPartialTicks Partial ticks from last render
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int mx, int my) {
		// Draw Background
		mc.getTextureManager().bindTexture(Textures.GUI.AURA_CONVERTER);
	    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
