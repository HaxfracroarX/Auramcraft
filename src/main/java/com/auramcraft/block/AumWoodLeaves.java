package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AumWoodLeaves extends Block {
	public AumWoodLeaves() {
		super(Material.grass);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 1);
		setStepSound(Block.soundTypeGrass);
		setBlockName(Names.Blocks.AUMWOODLEAVES);
		setBlockTextureName(Textures.BLOCK_AUM_WOOD_LEAVES);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
}
