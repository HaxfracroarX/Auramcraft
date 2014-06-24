package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.inventory.ContainerEmpty;
import com.auramcraft.item.pages.*;
import com.auramcraft.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiBookOfAura extends GuiContainer {
	BookPage page;
	
	public GuiBookOfAura() {
		super(new ContainerEmpty());
		xSize = 146;
		ySize = 180;
		page = new PageShards();
	}
	
	@Override
	public void initGui() {
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    int trueRightX, trueLeftX;
	    y += 158;
	    x += 15;
	    trueRightX = x;
	    x += 90;
	    trueLeftX = x;
	    
	    buttonList.add(new ButtonTurnPage(0, trueLeftX, y, false));
	    buttonList.add(new ButtonTurnPage(1, trueRightX, y, true));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(page.getTexture());
		
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    drawTexturedModalRect(x, y, 20, 1, xSize, ySize);
	}
}
