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
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(AuramcraftBlocks.aumwoodSapling);
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
}
