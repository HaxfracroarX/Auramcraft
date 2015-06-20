package com.auramcraft.block;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.RenderIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileAlchemicalRouter;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AlchemicalRouter extends TEBlock {
	public AlchemicalRouter() {
		super(Material.iron);
		setHardness(3f);
		setBlockName(Names.Blocks.ALCHEMICAL_ROUTER);
		setBlockTextureName(Textures.Blocks.ALCHEMICAL_ROUTER);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.alchemicalRouter;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileAlchemicalRouter();
	}
}
