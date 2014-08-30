package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class AllseeingStone extends Item {
	public AllseeingStone() {
		setTextureName(Textures.Items.ITEM_ALLSEEING_STONE);
		setUnlocalizedName(Names.Items.ALLSEEING_STONE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
