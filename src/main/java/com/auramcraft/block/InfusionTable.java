package com.auramcraft.block;

import java.util.Random;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.GUIIds;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class InfusionTable extends BlockContainer {
	public InfusionTable() {
		super(Material.iron);
		setHardness(3f);
		setBlockName("infusionTable");
		setBlockTextureName(Textures.Blocks.BLOCK_INFUSION_TABLE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.infusionTable;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityInfusionTable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		if(world.getTileEntity(x, y, z) instanceof TileEntityInfusionTable) {
			int direction = 0;
			int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			
			if(facing == 0)
				direction = ForgeDirection.NORTH.ordinal();
			else if(facing == 1)
				direction = ForgeDirection.EAST.ordinal();
			else if(facing == 2)
				direction = ForgeDirection.SOUTH.ordinal();
			else if(facing == 3)
				direction = ForgeDirection.WEST.ordinal();
			
			((TileEntityInfusionTable) world.getTileEntity(x, y, z)).setOrientation(direction);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityInfusionTable)
			player.openGui(Auramcraft.instance, GUIIds.INFUSION_TABLE, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	protected void dropInventory(World world, int x, int y, int z) {
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
