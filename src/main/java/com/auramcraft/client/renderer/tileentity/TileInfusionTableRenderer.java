package com.auramcraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import com.auramcraft.client.renderer.model.ModelInfusionTable;
import com.auramcraft.reference.Models;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileInfusionTableRenderer extends TileAuramcraftRenderer {
	public TileInfusionTableRenderer() {
		super(Models.infusionTable, Textures.Models.MODEL_INFUSION_TABLE);
	}
}
