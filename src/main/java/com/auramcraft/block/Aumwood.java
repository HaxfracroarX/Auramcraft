package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class Aumwood extends Block {
	@SideOnly(Side.CLIENT)
	private static IIcon topIcon;
	private static IIcon sideIcon;
	
	public Aumwood() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 2);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.AUMWOOD);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Textures.Blocks.AUMWOOD_TOP);
		sideIcon = iconRegister.registerIcon(Textures.Blocks.AUMWOOD_SIDE);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		int pos = metadata & 12;
		
		if(pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
			return topIcon;
		return sideIcon;
	 }
}
