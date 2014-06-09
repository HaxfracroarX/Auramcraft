package com.auramcraft.block;

import com.auramcraft.Auramcraft;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class InfusionTable extends Block implements ITileEntityProvider {
	// Texures (Will be used once made)
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon frontIcon;
	
	public InfusionTable() {
		super(Material.iron);
		setHardness(3f);
		setBlockName("infusionTable");
		//registerIcons();
		setBlockTextureName(Auramcraft.MODID + ":GemstoneOre"); // Temporary filler
		setCreativeTab(Auramcraft.auramcraftTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityInfusionTable();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityInfusionTable)
			player.openGui(Auramcraft.instance, 0, world, x, y, z);
		return true;
	}
	
	// Will be used when textures are available
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Auramcraft.MODID + ":infusionTop");
		sideIcon = iconRegister.registerIcon(Auramcraft.MODID + ":infusionSide");
		frontIcon = iconRegister.registerIcon(Auramcraft.MODID + ":infusionFront");
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		if(side == 0 || side == 1)
			return topIcon;
		else if(side != metadata)
			return sideIcon;
		return frontIcon;
	}
}
