package com.auramcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public abstract class TEBlock extends BlockContainer {
	
	public TEBlock(Material material) {
		super(material);
	}
	
	@Override
	public abstract TileEntity createNewTileEntity(World world, int metadata);
	
	@Override
	public abstract int getRenderType();
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	private void dropInventory(World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if(!(tileEntity instanceof IInventory))
			return;
		
		IInventory inventory = (IInventory) tileEntity;
		
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			
			if(itemStack != null && itemStack.stackSize > 0) {
				Random rand = new Random();
				
				float dX = rand.nextFloat() * 0.8F + 0.1F;
				float dY = rand.nextFloat() * 0.8F + 0.1F;
				float dZ = rand.nextFloat() * 0.8F + 0.1F;
				
				EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
				
				if(itemStack.hasTagCompound())
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
				
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				itemStack.stackSize = 0;
			}
		}
	}
}
