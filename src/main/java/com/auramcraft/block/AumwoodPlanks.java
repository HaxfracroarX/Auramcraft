package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AumwoodPlanks extends Block {
	public AumwoodPlanks() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 2);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.AUMWOOD_PLANKS);
		setBlockTextureName(Textures.Blocks.AUMWOOD_PLANKS);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
