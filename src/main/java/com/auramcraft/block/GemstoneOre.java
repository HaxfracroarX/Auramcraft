package com.auramcraft.block;

import java.util.Random;
import com.auramcraft.Auramcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class GemstoneOre extends Block {
	public GemstoneOre() {
		super(Material.rock);
		setHardness(3f);
		setResistance(5f);
		setHarvestLevel("pickaxe", 2);
		setStepSound(Block.soundTypeStone);
		setBlockName("gemstoneOre");
		setBlockTextureName(Auramcraft.MODID + ":GemstoneOre");
		setCreativeTab(Auramcraft.auramcraftTab);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Auramcraft.airShard;
	}
}
