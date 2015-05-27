package com.auramcraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import com.auramcraft.tileentity.TileAuramcraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

@SuppressWarnings("WeakerAccess")
public class TileAuramcraftRenderer extends TileEntitySpecialRenderer {
	private final ModelBase model;
	private final ResourceLocation texture;
	
	public TileAuramcraftRenderer(ModelBase model, ResourceLocation texture) {
		this.model = model;
		this.texture = texture;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTime) {
		render((TileAuramcraft) tileEntity, x, y, z);
	}
	
	public void render(TileAuramcraft tileEntity, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		// Get angle from orientation
		ForgeDirection direction = tileEntity.getOrientation();
		short angle = 0;
		switch(direction) {
			case NORTH:
				angle = 180;
				break;
			case SOUTH:
				angle = 0;
				break;
			case WEST:
				angle = 90;
				break;
			case EAST:
				angle = -90;
				break;
			default:
		}
		
		// Translate, Rotate
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180F, -1F, 0F, 0F);
		GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
		
		// Bind Texture
		bindTexture(texture);
		
		// Render
		model.render(null, 0f, 0f, -0.1f, 0f, 0f, 0.0625f);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
