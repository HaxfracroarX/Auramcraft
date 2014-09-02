package com.auramcraft.generation;

import java.util.Random;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.util.ForgeDirection;

public class AumwoodTreeGen extends WorldGenAbstractTree implements IWorldGenerator {
	private boolean isFruitTree;
	
	public AumwoodTreeGen() {
		super(true);
		this.isFruitTree = false;
	}
	
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
	
	private void generateInOverworld(World world, Random random, int i, int j) {
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(i, j);
		if (biomegenbase.biomeID == BiomeGenBase.sky.biomeID || 
				biomegenbase.biomeID == BiomeGenBase.hell.biomeID || 
				world.getWorldInfo().getTerrainType().getWorldTypeID() == WorldType.FLAT.getWorldTypeID())
			return;
		
		if(random.nextInt(100) == 0) {
			int x = i + random.nextInt(16);
			int z = j + random.nextInt(16);
			int y = world.getHeightValue(x, z);
			generate(world, random, x, y, z);
		}
	}
	
	private void generateInEnd(World world, Random random, int i, int j) {
		
	}

	private void generateInNether(World world, Random random, int i, int j) {
		
	}
	
	public boolean generate(World world, Random random, int x, int y, int z) {
		int l = random.nextInt(3) + 5;
		
		if(this.isFruitTree)
			l += random.nextInt(7);
		
		boolean flag = true;
		
		if(y >= 1 && y + l + 1 <= 256) {
			int j1;
			int k1;
			
			for(int i1 = y; i1 <= y + 1 + l; ++i1) {
				byte b0 = 1;
				
				if(i1 == y)
					b0 = 0;
				
				if(i1 >= y + 1 + l - 2)
					b0 = 2;
				
				for(j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
					for(k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
						if(i1 >= 0 && i1 < 256) {
							Block block = world.getBlock(j1, i1, k1);
							
							if(!this.isReplaceable(world, j1, i1, k1)) {
								flag = false;
							}
						}
						else
							flag = false;
					}
				}
			}
			
			if(!flag)
				return false;
			else {
				Block block2 = world.getBlock(x, y - 1, z);
				
				boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) AuramcraftBlocks.aumWoodSapling);
				if(isSoil && y < 256 - l - 1) {
					block2.onPlantGrow(world, x, y - 1, z, x, y, z);
					int k2;
					
					for(k2 = y - 3 + l; k2 <= y + l; ++k2) {
						j1 = k2 - (y + l);
						k1 = 1 - j1 / 2;
						
						for(int l2 = x - k1; l2 <= x + k1; ++l2) {
							int l1 = l2 - x;
							
							for(int i2 = z - k1; i2 <= z + k1; ++i2) {
								int j2 = i2 - z;
								
								if(Math.abs(l1) != k1 || Math.abs(j2) != k1 || random.nextInt(2) != 0 && j1 != 0) {
									Block block1 = world.getBlock(l2, k2, i2);
									
									if(block1.isAir(world, l2, k2, i2) || block1.isLeaves(world, l2, k2, i2)) {
										this.setBlockAndNotifyAdequately(world, l2, k2, i2, AuramcraftBlocks.aumWoodLeaves, 2);
									}
								}
							}
						}
					}
					
					for(k2 = 0; k2 < l; ++k2) {
						Block block3 = world.getBlock(x, y + k2, z);
						
						if(block3.isAir(world, x, y + k2, z) || block3.isLeaves(world, x, y + k2, z)) {
							this.setBlockAndNotifyAdequately(world, x, y + k2, z, AuramcraftBlocks.aumWood, 2);
						}
					}
					
					return true;
				}
				else
					return false;
			}
		}
		else
			return false;
	}
}