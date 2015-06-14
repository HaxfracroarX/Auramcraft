package com.auramcraft.block;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.*;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class InfusionTable extends TEBlock {
	public InfusionTable() {
		super(Material.iron);
		setHardness(3f);
		setBlockName(Names.Blocks.INFUSION_TABLE);
		setBlockTextureName(Textures.Blocks.BLOCK_INFUSION_TABLE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.infusionTable;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileInfusionTable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		if(world.getTileEntity(x, y, z) instanceof TileInfusionTable) {
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
			
			((TileInfusionTable) world.getTileEntity(x, y, z)).setOrientation(direction);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		player.openGui(Auramcraft.instance, GUIIds.INFUSION_TABLE, world, x, y, z);
		
		// Infusion Page
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
		int tabID = BookIds.getID(BookIds.infusion);
		int pageID = BookIds.pageInfusion.getID();
		boolean[] pages = stats.getPages(tabID);
		boolean firstTime = !pages[pageID];
		
		
		pages[pageID] = true;
		stats.setPages(tabID, pages);
		
		if(firstTime)
			stats.initPageAnnouncment("Infusion");
		
		return true;
	}
}
