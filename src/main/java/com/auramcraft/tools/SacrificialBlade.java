package com.auramcraft.tools;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import net.minecraft.item.ItemSword;

public class SacrificialBlade extends ItemSword {
	public SacrificialBlade() {
		super(ToolMaterials.sacrificialMaterial);
		setUnlocalizedName(Names.Items.SACRIFICIALBLADE);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setTextureName(Reference.MODID + ":SacrificalDagger");
	}
}
