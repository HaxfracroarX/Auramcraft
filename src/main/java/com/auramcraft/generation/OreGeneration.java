package com.auramcraft.generation;

import java.util.Random;
import com.auramcraft.Auramcraft;
import com.auramcraft.init.AuramcraftBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
			case -1:
				generateInNether(world, random, chunkX*16, chunkZ*16);
				break;
			case 0:
				generateInOverworld(world, random, chunkX*16, chunkZ*16);
				break;
			case 1:
				generateInEnd(world, random, chunkX*16, chunkZ*16);
				break;
			default:
				generateInOverworld(world, random, chunkX*16, chunkZ*16);
				break;
		}
	}
	
	private void generateInOverworld(World world, Random random, int x, int z) {
		// 20 is how many times it will generate a node in a chunk
		for(int i = 0; i < 20; i++) {
			int chunkX = x + random.nextInt(16);
			int chunkY = random.nextInt(50); // 50 is the highest y level to generate
			int chunkZ = z + random.nextInt(16);
			
			// 10 is the biggest node size
			(new WorldGenMinable(AuramcraftBlocks.gemstoneOre, 10)).generate(world, random, chunkX, chunkY, chunkZ);
		}
	}
	
	private void generateInEnd(World world, Random random, int x, int z) {
		// No plans for End
	}
	
	private void generateInNether(World world, Random random, int x, int z) {
		// No plans for Nether
	}
}
