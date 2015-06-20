package com.auramcraft.client.gui.inventory;

import com.auramcraft.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("WeakerAccess")
public class ButtonTurnPage extends GuiButton {
	private final boolean isRight;
	
	public ButtonTurnPage(int id, int x, int y, boolean isRight) {
		super(id, x, y, 18, 10, "");
		this.isRight = isRight;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		if(this.visible) {
			boolean isHovering = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
			GL11.glColor4f(1f, 1f, 1f, 1f);
			mc.getTextureManager().bindTexture(Textures.GUI.BOOK_OF_AURA);
			int tx = 3;
			int ty = 0;
			
			if(isHovering)
				tx += 23;
			
			if(isRight)
				ty += 194;
			else
				ty += 207;
			
			drawTexturedModalRect(xPosition, yPosition, tx, ty, width, height);
		}
	}
}
