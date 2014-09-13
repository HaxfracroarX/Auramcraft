package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.api.Auras;
import com.auramcraft.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class AuraButton extends GuiButton {
	private ResourceLocation texture;
	private Auras aura;
	
	public AuraButton(int id, int x, int y, Auras aura) {
		super(id, x, y, 30, 30, "");
		this.texture = Textures.GUI.GUI_INFUSION_TABLE_AURAS;
		this.aura = aura;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		if(this.visible) {
			GL11.glColor4f(1f, 1f, 1f, 1f);
			mc.getTextureManager().bindTexture(texture);
			drawTexturedModalRect(xPosition, yPosition, ((aura.getTier()-1)*32)+1, (aura.getId()*32)+1, width, height);
		}
	}
}
