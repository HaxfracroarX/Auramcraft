package com.auramcraft.block;

import java.util.ArrayList;
import java.util.Random;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Names.Items;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class AumWoodLeaves extends BlockLeavesBase implements IShearable {
	@SideOnly(Side.CLIENT)
	private IIcon texture;
	
	int[] tree;
	
	public AumWoodLeaves() {
		super(Material.leaves, true);
		setHardness(1f);
		setResistance(1f);
		setStepSound(Block.soundTypeGrass);
		setBlockName(Names.Blocks.AUMWOODLEAVES);
		setBlockTextureName(Textures.BLOCK_AUMWOOD_LEAVES);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setTickRandomly(true);
		setLightOpacity(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		texture = register.registerIcon(Textures.BLOCK_AUMWOOD_LEAVES);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return texture;
	}
	
	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> itemStack = new ArrayList<ItemStack>();
		itemStack.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return itemStack;
	}
	
	private void removeLeaves(World world, int x, int y, int z) {
		this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}
	
	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		int i2 = world.getBlockMetadata(x, y, z);
		
		if((i2 & 8) == 0)
			world.setBlockMetadataWithNotify(x, y, z, i2 | 8, 4);
		
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int fortune) {
		byte b0 = 1;
		int i1 = b0 + 1;
		
		if(world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for(int j1 = -b0; j1 <= b0; ++j1) {
				for(int k1 = -b0; k1 <= b0; ++k1) {
					for(int l1 = -b0; l1 <= b0; ++l1) {
						Block block2 = world.getBlock(x + j1, y + k1, z + l1);
						if(block2.isLeaves(world, x + j1, y + k1, z + l1))
							block2.beginLeavesDecay(world, x + j1, y + k1, z + l1);
					}
				}
			}
		}
	}
	
	@Override
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, 1.0f, p_149690_7_);
    }

    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_) {}

    protected int func_150123_b(int p_150123_1_)
    {
        return 1;
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if(!world.isRemote) {
			int l = world.getBlockMetadata(x, y, z);
			
			if((l & 8) != 0 && (l & 4) == 0) {
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;
				
				if(tree == null)
					tree = new int[b1 * b1 * b1];
				
				int l1;
				
				if(world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
					int i2;
					int j2;
					
					for(l1 = -b0; l1 <= b0; ++l1) {
						for(i2 = -b0; i2 <= b0; ++i2) {
							for(j2 = -b0; j2 <= b0; ++j2) {
								Block block = world.getBlock(x + l1, y + i2, z + j2);
								
								if(!block.canSustainLeaves(world, x + l1, y + i2, z + j2)) {
									if(block.isLeaves(world, x + l1, y + i2, z + j2))
										tree[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
									else
										tree[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
								}
								else
									tree[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
							}
						}
					}
					
					for(l1 = 1; l1 <= 4; ++l1) {
						for(i2 = -b0; i2 <= b0; ++i2) {
							for(j2 = -b0; j2 <= b0; ++j2) {
								for(int k2 = -b0; k2 <= b0; ++k2) {
									if(tree[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
										if(tree[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
											tree[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										
										if(tree[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
											tree[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										
										if(tree[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
											tree[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
										
										if(tree[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
											tree[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
										
										if(tree[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
											tree[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
										
										if(tree[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
											tree[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
									}
								}
							}
						}
					}
				}
				
				l1 = tree[k1 * j1 + k1 * b1 + k1];
				
				if(l1 >= 0)
					world.setBlockMetadataWithNotify(x, y, z, l & -9, 4);
				else
					this.removeLeaves(world, x, y, z);
			}
		}
	}
}
