package com.auramcraft.item.tools;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.ItemSword;

public class SacrificialDagger extends ItemSword {
	public SacrificialDagger() {
		super(ToolMaterials.sacrificialMaterial);
		setUnlocalizedName(Names.Items.SACRIFICIAL_DAGGER);
		setTextureName(Textures.Items.SACRIFICIAL_DAGGER);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
