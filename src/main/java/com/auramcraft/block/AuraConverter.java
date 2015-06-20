package com.auramcraft.block;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.GUIIds;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileAuraConverter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AuraConverter extends TEBlock {
	public AuraConverter() {
		super(Material.iron);
		setHardness(3f);
		setBlockName(Names.Blocks.AURA_CONVERTER);
		setBlockTextureName(Textures.Blocks.AURA_CONVERTER);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileAuraConverter();
	}
	
	@Override
	public int getRenderType() {
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sx, float sy, float sz) {
		player.openGui(Auramcraft.instance, GUIIds.AURA_CONVERTER, world, x, y, z);
		return true;
	}
}
