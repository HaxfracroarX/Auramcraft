package com.auramcraft.block;

import java.util.List;
import java.util.Random;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.generation.AumwoodWorldGenTree;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import com.auramcraft.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AumWoodSapling extends BlockSapling {
	private IIcon texture;
	
    public AumWoodSapling()
    {
    	setHardness(1f);
    	setStepSound(soundTypeGrass);
        setBlockName(Names.Blocks.AUMWOODSAPLING);
        setCreativeTab(CreativeTab.AuramcraftTab);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
    	texture = register.registerIcon(Textures.BLOCK_AUMWOOD_SAPLING);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
    	return texture;
    }
    
    public boolean isValidPosition(World world, int x, int y, int z, int metadata) {
    	Block block = world.getBlock(x, y, z);
    	return block == Blocks.dirt || 
    			block == Blocks.grass || 
    			block == Blocks.farmland || 
    			block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
    	return isValidPosition(world, x, y, z, -1);
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
    	Block soil = world.getBlock(x, y, z);
    	
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
				(soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
    	if(!world.isRemote) {
    		if(world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
    			func_149878_d(world, x, y, z, random); // Grows the tree
    	}
    }
    
    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random) {
    	int meta = world.getBlockMetadata(x, y, z) & 15;
		Object obj = null;
		int rnd = random.nextInt(8);

		if (obj == null)
			obj = new AumwoodWorldGenTree(true, true);
		
		if(obj != null) {
			world.setBlockToAir(x, y, z);
			
			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) 
				world.setBlock(x, y, z, this, meta, 2);
		}
    }
}