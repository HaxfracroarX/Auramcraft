package com.auramcraft.block;

import java.util.Random;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GemstoneOre extends Block {
	public GemstoneOre() {
		super(Material.rock);
		setHardness(3f);
		setResistance(5f);
		setHarvestLevel("pickaxe", 2);
		setStepSound(Block.soundTypeStone);
		setBlockName(Names.Blocks.GEMSTONEORE);
		setBlockTextureName(Textures.BLOCK_GEMSTONE_ORE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return AuramcraftItems.gemstone;
	}
}
