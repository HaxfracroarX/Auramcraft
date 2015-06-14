package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AuroraBlock extends Block {
		public AuroraBlock() {
			super(Material.rock);
			setHardness(0.1F);
			setResistance(10F);
			setBlockName(Names.Blocks.AURORA_BLOCK);
			setCreativeTab(CreativeTab.AuramcraftTab);
			setBlockTextureName(Textures.Blocks.BLOCK_AURORA_BLOCK);
		}

}
