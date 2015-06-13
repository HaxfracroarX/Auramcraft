package com.auramcraft.proxy;

import com.auramcraft.client.renderer.item.ItemAuramcraftRenderer;
import com.auramcraft.client.renderer.tileentity.TileAuramcraftRenderer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Models;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileAlchemicalRouter;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.tileentity.TileStorageJar;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		// Render ids
		RenderIds.infusionTable = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.storageJar = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.alcemicalRouter = RenderingRegistry.getNextAvailableRenderId();
		
		// Register Item renderer
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.infusionTable), new ItemAuramcraftRenderer(Models.infusionTable, Textures.Models.MODEL_INFUSION_TABLE));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.storageJar), new ItemAuramcraftRenderer(Models.storageJar, Textures.Models.MODEL_STORAGE_JAR));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.alchemicalRouter), new ItemAuramcraftRenderer(Models.alchemicalRouter, Textures.Models.MODEL_ALCHEMICAL_ROUTER));
		
		// Register Block renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfusionTable.class, new TileAuramcraftRenderer(Models.infusionTable, Textures.Models.MODEL_INFUSION_TABLE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileStorageJar.class, new TileAuramcraftRenderer(Models.storageJar, Textures.Models.MODEL_STORAGE_JAR));
		ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalRouter.class, new TileAuramcraftRenderer(Models.alchemicalRouter, Textures.Models.MODEL_ALCHEMICAL_ROUTER));
	}
}
