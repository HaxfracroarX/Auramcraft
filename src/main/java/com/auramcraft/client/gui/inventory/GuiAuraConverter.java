package com.auramcraft.client.gui.inventory;

import com.auramcraft.inventory.ContainerAuraConverter;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileAuraConverter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiAuraConverter extends GuiContainer {
	private TileAuraConverter tileEntity;
	
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
		// TODO: Draw Aura info
	}
	
	/**
	 * @param renderPartialTicks Partial ticks from last render
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int mx, int my) {
		// Draw Background
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Textures.GUI.AURA_CONVERTER);
	    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
