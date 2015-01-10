package com.auramcraft.block;

import java.util.Random;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.GUIIds;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.tileentity.TileStorageJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
