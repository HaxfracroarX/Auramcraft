package com.auramcraft.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import com.auramcraft.client.renderer.item.ItemInfusionTableRenderer;
import com.auramcraft.client.renderer.tileentity.TileEntityInfusionTableRenderer;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		// Render ids
		RenderIds.infusionTable = RenderingRegistry.getNextAvailableRenderId();
		
		// Register Item renderer
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AuramcraftBlocks.infusionTable), new ItemInfusionTableRenderer());
		
		// Register Block renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfusionTable.class, new TileEntityInfusionTableRenderer());
	}
}
