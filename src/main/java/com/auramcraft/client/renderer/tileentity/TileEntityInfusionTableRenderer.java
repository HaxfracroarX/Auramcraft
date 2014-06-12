package com.auramcraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import com.auramcraft.client.renderer.model.ModelInfusionTable;
import com.auramcraft.reference.Reference;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityInfusionTableRenderer extends TileEntitySpecialRenderer {
	public ModelInfusionTable model = new ModelInfusionTable();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTime) {
		TileEntityInfusionTable tileEntityInfusionTable = (TileEntityInfusionTable) tileEntity;
		render(tileEntityInfusionTable, x, y, z);
	}
	
	public void render(TileEntityInfusionTable tileEntity, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5d, y + 1.5d, z + 0.5d);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "/textures/blocks/infusionTable.png"));
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1f, -1f, -1f);
		model.render((Entity)null, 0f, 0f, -0.1f, 0f, 0f, 0.0625f);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1f, 1f, 1f);
		GL11.glPopMatrix();
	}
}
