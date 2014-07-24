package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class AuraCrystal extends AuraItem {
	public AuraCrystal(int maxAura) {
		super(maxAura, 1);
		setTextureName(Textures.Items.ITEM_AURA_CRYSTAL);
		setUnlocalizedName(Names.Items.AURACRYSTAL);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setMaxStackSize(1);
	}
}
