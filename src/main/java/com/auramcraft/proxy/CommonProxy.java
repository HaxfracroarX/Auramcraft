package com.auramcraft.proxy;

import com.auramcraft.Auramcraft;
import com.auramcraft.handler.AuramcraftEventHandler;
import com.auramcraft.handler.AuramcraftGuiHandler;
import com.auramcraft.reference.Names;
import com.auramcraft.tileentity.TileAlchemicalRouter;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.tileentity.TileStorageJar;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public class CommonProxy implements IProxy {
	/** Used to store AuramcraftPlayerStats data temporarily between player death and respawn */
	private static final Map<String, NBTTagCompound> playerData = new HashMap<String, NBTTagCompound>();
	
	@Override
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileInfusionTable.class, "tile." + Names.Blocks.INFUSIONTABLE);
		GameRegistry.registerTileEntity(TileStorageJar.class, "tile." + Names.Blocks.STORAGEJAR);
		GameRegistry.registerTileEntity(TileAlchemicalRouter.class, "tile." + Names.Blocks.ALCHEMICALROUTER);
	}
	
	@Override
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new AuramcraftEventHandler());
		FMLCommonHandler.instance().bus().register(new AuramcraftEventHandler());
	}
	
	@Override
	public void registerGUIHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Auramcraft.instance, new AuramcraftGuiHandler());
	}

	@Override
	public void registerRenderers() {
		// Servers don't need renderers!
	}
	
	@Override
	public void storeEntityData(EntityPlayer player, NBTTagCompound compound) {
		playerData.put(player.getDisplayName(), compound);
	}
	
	@Override
	public NBTTagCompound getEntityData(EntityPlayer player) {
		return playerData.remove(player.getDisplayName());
	}
}
