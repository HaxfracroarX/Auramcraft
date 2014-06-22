package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.inventory.ContainerEmpty;
import com.auramcraft.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiBookOfAura extends GuiContainer {
	public GuiBookOfAura() {
		super(new ContainerEmpty());
		xSize = 165;
		ySize = 180;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_BOOK_OF_AURA);
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    drawTexturedModalRect(x, y, 20, 1, xSize, ySize);
	}
}
