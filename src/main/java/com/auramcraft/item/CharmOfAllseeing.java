package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class CharmOfAllseeing extends Item {
	public CharmOfAllseeing() {
		setTextureName(Textures.Items.CHARM_OF_ALLSEEING);
		setUnlocalizedName(Names.Items.CHARM_OF_ALLSEEING);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
