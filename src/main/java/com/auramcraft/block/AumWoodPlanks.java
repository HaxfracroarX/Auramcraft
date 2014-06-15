package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AumWoodPlanks extends Block {
	public AumWoodPlanks() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 2);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.AUMWOODPLANKS);
		setBlockTextureName(Textures.BLOCK_AUMWOOD_PLANKS);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
