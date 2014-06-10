package com.auramcraft.tools;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;

import net.minecraft.item.ItemSword;

public class sacrificialBlade extends ItemSword {

	public sacrificialBlade(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName(Names.Items.SACRIFICIALBLADE);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setTextureName(Reference.MODID + ":SacrificialBlade");
	}

}
