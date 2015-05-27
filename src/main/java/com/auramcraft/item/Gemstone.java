package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class Gemstone extends Item {
	public Gemstone() {
		setUnlocalizedName(Names.Items.GEMSTONE);
		setTextureName(Textures.Items.ITEM_GEMSTONE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
