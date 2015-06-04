package com.auramcraft.proxy;

import com.auramcraft.client.renderer.item.ItemAlchemicalRouterRenderer;
import com.auramcraft.client.renderer.item.ItemInfusionTableRenderer;
import com.auramcraft.client.renderer.item.ItemStorageJarRenderer;
import com.auramcraft.client.renderer.tileentity.TileAlchemicalRouterRenderer;
import com.auramcraft.client.renderer.tileentity.TileInfusionTableRenderer;
import com.auramcraft.client.renderer.tileentity.TileStorageJarRenderer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.RenderIds;
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
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.infusionTable), new ItemInfusionTableRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.storageJar), new ItemStorageJarRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.alchemicalRouter), new ItemAlchemicalRouterRenderer());
		
		// Register Block renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfusionTable.class, new TileInfusionTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileStorageJar.class, new TileStorageJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalRouter.class, new TileAlchemicalRouterRenderer());
	}
}
