package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.generation.AumwoodTreeGen;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

@SuppressWarnings("unchecked")
public class AumwoodSapling extends BlockSapling {
	@SideOnly(Side.CLIENT)
	private IIcon texture;
	
    public AumwoodSapling()
    {
    	setHardness(1f);
    	setStepSound(soundTypeGrass);
        setBlockName(Names.Blocks.AUMWOOD_SAPLING);
        setCreativeTab(CreativeTab.AuramcraftTab);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
    	texture = register.registerIcon(Textures.Blocks.BLOCK_AUMWOOD_SAPLING);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
    	return texture;
    }
    
    private boolean isValidPosition(World world, int x, int y, int z, int metadata) {
    	Block block = world.getBlock(x, y - 1, z);
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
    	return isValidPosition(world, x, y, z, -1);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
    	if(!world.isRemote) {
    		if(world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
    			func_149878_d(world, x, y, z, random); // Grows the tree
    	}
    }
    /**
     * Grows a tree
     */
    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random) {
    	int meta = world.getBlockMetadata(x, y, z) & 15;
		Object obj = null;
		int rnd = random.nextInt(8);

		if (obj == null)
			obj = new AumwoodTreeGen();
		
		if(obj != null) {
			world.setBlockToAir(x, y, z);
			
			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) 
				world.setBlock(x, y, z, this, meta, 2);
		}
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item, 1, 0));
    }
}
