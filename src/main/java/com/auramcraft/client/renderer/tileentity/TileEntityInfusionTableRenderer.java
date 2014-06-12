package com.auramcraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import com.auramcraft.client.renderer.model.ModelInfusionTable;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityInfusionTableRenderer extends TileEntitySpecialRenderer {
	public ModelInfusionTable model = new ModelInfusionTable();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTime) {
		TileEntityInfusionTable tileEntityInfusionTable = (TileEntityInfusionTable) tileEntity;
		render(tileEntityInfusionTable, x, y, z);
	}
	
	public void render(TileEntityInfusionTable tileEntity, double x, double y, double z) {
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
		bindTexture(Textures.MODEL_INFUSION_TABLE);
		
		// Render
		model.render((Entity)null, 0f, 0f, -0.1f, 0f, 0f, 0.0625f);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
