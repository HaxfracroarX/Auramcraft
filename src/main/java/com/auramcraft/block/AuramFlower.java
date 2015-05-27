package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AuramFlower extends BlockBush {
	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public AuramFlower() {
		super(Material.plants);
		setCreativeTab(CreativeTab.AuramcraftTab);
    	setHardness(1f);
        setBlockName(Names.Blocks.AURAMFLOWER);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
    	texture = register.registerIcon(Textures.Blocks.BLOCK_AURAM_FLOWER);
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
    			block == Blocks.sand ||
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
	
}
