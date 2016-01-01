package com.auramcraft.client.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemAuramcraftRenderer implements IItemRenderer {
	private final ModelBase model;
	private final ResourceLocation texture;
	
	public ItemAuramcraftRenderer(ModelBase model, ResourceLocation texture) {
		this.model = model;
		this.texture = texture;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type) {
			case ENTITY:
				render(-0.5f, -0.38f, 0.5f, type);
				break;
			case EQUIPPED:
				render(0f, 0f, 1f, type);
				break;
			case EQUIPPED_FIRST_PERSON:
				render(0f, 0f, 1f, type);
				break;
			case INVENTORY:
				render(-1f, -0.9f, 0f, type);
				break;
			default:
		}
	}
	
	private void render(float x, float y, float z, ItemRenderType type) {
		GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        // Scale, Translate, Rotate
        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslated(x + 0.5, y + 1.5, z - 0.5);
        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        	GL11.glRotatef(180F, -1F, 0F, 0F);
        else
        	GL11.glRotatef(180F, -1F, 0F, 1F);
        
        // Bind texture
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        
        // Render
        model.render(null, 0f, 0f, -0.1f, 0f, 0f, 0.0625f);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
}
