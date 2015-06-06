package com.auramcraft.client.gui.inventory;

import com.auramcraft.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class ButtonBookTab extends GuiButton {
	public ButtonBookTab(int id, int x, int y) {
		super(id, x, y, 17, 34, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		GL11.glColor4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_BOOK_OF_AURA);
		
		drawTexturedModalRect(xPosition, yPosition, 191 + ((id - 3) * 19), 8, width, height);
	}
}
