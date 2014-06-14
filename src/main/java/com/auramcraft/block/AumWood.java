package com.auramcraft.block;

import javax.swing.Icon;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AumWood extends Block {
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	public static IIcon sideIcon;
	public static IIcon bottomIcon;
	
	public AumWood() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 2);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.AUMWOOD);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Textures.BLOCK_AUM_WOOD_TOP);
		sideIcon = iconRegister.registerIcon(Textures.BLOCK_AUM_WOOD_SIDE);
		bottomIcon = iconRegister.registerIcon(Textures.BLOCK_AUM_WOOD_TOP);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		if(side == 0 || side == 1)
			return topIcon;
		else if(side != metadata)
			return sideIcon;
		return sideIcon;
	 }
}
