package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ShadowLantern extends Block {
	@SideOnly(Side.CLIENT)
	private static IIcon topIcon;
	private static IIcon sideIcon;
	private static IIcon frontIcon;
	
	public ShadowLantern() {
		super(Material.wood);
		setHardness(1f);
		setResistance(1f);
		setHarvestLevel("axe", 1);
		setStepSound(Block.soundTypeWood);
		setBlockName(Names.Blocks.SHADOWLANTERN);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setLightLevel(0.6F);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		if(world.getBlock(x, y, z) instanceof ShadowLantern) {
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
			
			// Add 1 to direction to avoid setting meta to 0, for reasons explained in getIcon.
			world.setBlockMetadataWithNotify(x, y, z, direction+1, 2);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_TOP);
		sideIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_SIDE);
		frontIcon = iconRegister.registerIcon(Textures.Blocks.BLOCK_SHADOW_LANTERN_FRONT);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1 || side == 0)
			return topIcon;
		
		// If meta = 0, then the block hasn't been placed nor gotten a direction.
		// Therefore, set side 3 to frontIcon to make it look good while waiting to be placed.
		if(meta == 0 & side == 3)
			return frontIcon;
		else {
			switch(ForgeDirection.getOrientation(meta - 1)) {
				case NORTH:
					if(side == 2)
						return frontIcon;
					break;
				case EAST:
					if(side == 5)
						return frontIcon;
					break;
				case SOUTH:
					if(side == 3)
						return frontIcon;
					break;
				case WEST:
					if(side == 4)
						return frontIcon;
					break;
				default:
					return sideIcon;
			}
		}
		
		return sideIcon;
	}
}
