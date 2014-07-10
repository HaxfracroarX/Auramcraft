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
import net.minecraftforge.common.util.ForgeDirection;

public class HolyLamp extends Block {
	
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	public static IIcon bottomIcon;
	
	public HolyLamp() {
		super(Material.glass);
		setHardness(1f);
		setResistance(1f);
		setStepSound(Block.soundTypeGlass);
		setBlockName(Names.Blocks.HOLYLAMP);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setLightLevel(1.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		bottomIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_HOLY_LAMP_BOTTOM);
		sideIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_HOLY_LAMP_SIDE);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1 || side == 0)
			return bottomIcon;
		
		return sideIcon;
	}
}
