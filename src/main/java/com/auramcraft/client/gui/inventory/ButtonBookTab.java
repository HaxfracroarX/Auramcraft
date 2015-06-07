package com.auramcraft.client.gui.inventory;

import com.auramcraft.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class ButtonBookTab extends GuiButton {
	public ButtonBookTab(int id, int x, int y) {
		super(id, x, y, 16, 16, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		// Check if hovering
		boolean isHovering = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
		
		// Calculate based on isHovering
		int hoverYPos = yPosition;
		int hoverY = 189;
		int hoverHeight = height;
		if(isHovering) {
			hoverYPos -= 16;
			hoverY += 17;
			hoverHeight += 16;
		}
		
		// Draw
		GL11.glColor4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_BOOK_OF_AURA);
		
		drawTexturedModalRect(xPosition, hoverYPos, 51 + ((id - 3) * 17), hoverY, width, hoverHeight);
	}
}
