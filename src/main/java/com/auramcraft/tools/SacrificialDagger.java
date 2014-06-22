package com.auramcraft.tools;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;
import net.minecraft.item.ItemSword;

public class SacrificialDagger extends ItemSword {
	public SacrificialDagger() {
		super(ToolMaterials.sacrificialMaterial);
		setUnlocalizedName(Names.Items.SACRIFICIALDAGGER);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setTextureName(Textures.Items.ITEM_SACRIFICIAL_DAGGER);
	}
}
