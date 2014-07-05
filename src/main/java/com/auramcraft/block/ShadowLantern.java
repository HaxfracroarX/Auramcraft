package com.auramcraft.block;

import javax.swing.Icon;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ShadowLantern extends Block {
	
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	public static IIcon sideIcon;
	public static IIcon frontIcon;
	
	public ShadowLantern() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 1);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.SHADOWLANTERN);
		setCreativeTab(CreativeTab.AuramcraftTab);
			
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_TOP);
		sideIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_SIDE);
		frontIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_FRONT);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta == 0 && side == 1)
			return frontIcon;
		return sideIcon;
	}
}
