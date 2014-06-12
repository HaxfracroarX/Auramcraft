package com.auramcraft.block;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Reference;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class InfusionTable extends Block implements ITileEntityProvider {
	public InfusionTable() {
		super(Material.iron);
		setHardness(3f);
		setBlockName("infusionTable");
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess b, int i, int j, int k, int l) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityInfusionTable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase e, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, e, stack);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityInfusionTable)
			player.openGui(Auramcraft.instance, 0, world, x, y, z);
		return true;
	}
}
