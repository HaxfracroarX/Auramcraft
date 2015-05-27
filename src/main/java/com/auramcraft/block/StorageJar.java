package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileStorageJar;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class StorageJar extends TEBlock {
	public StorageJar() {
		super(Material.iron);
		setHardness(3f);
		setBlockName(Names.Blocks.STORAGEJAR);
		setBlockTextureName(Textures.Blocks.BLOCK_STORAGE_JAR);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.storageJar;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileStorageJar();
	}
}
