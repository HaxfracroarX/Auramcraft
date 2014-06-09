package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import net.minecraft.item.Item;

public class Gemstone extends Item {
	public Gemstone() {
		setUnlocalizedName(Names.Items.GEMSTONE);
		setTextureName(Reference.MODID + ":Gemstone");
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
