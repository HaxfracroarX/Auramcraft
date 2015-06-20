package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlueLightstone extends Block {
	public BlueLightstone() {
		super(Material.glass);
		setHardness(3F);
		setResistance(7F);
		setHarvestLevel("pickaxe", 1);
		setStepSound(Block.soundTypeGlass);
		setBlockName(Names.Blocks.BLUE_LIGHTSTONE);
		setBlockTextureName(Textures.Blocks.BLUE_LIGHTSTONE);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setLightOpacity(0);
		setLightLevel(0.9F);
	}
}
