package com.auramcraft.client.renderer.tileentity;

import com.auramcraft.client.renderer.model.ModelStorageJar;
import com.auramcraft.reference.Models;
import com.auramcraft.reference.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class TileStorageJarRenderer extends TileAuramcraftRenderer {
	public TileStorageJarRenderer() {
		super(Models.storageJar, Textures.Models.MODEL_STORAGE_JAR);
	}
}
