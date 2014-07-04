package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class AuraCrystal extends IAuraContainer {
	private static final int maxAura = 25;
	
	public AuraCrystal() {
		super(maxAura, 1);
		setTextureName(Textures.Items.ITEM_AURA_CRYSTAL);
		setUnlocalizedName(Names.Items.AURACRYSTAL);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public boolean canStoreAura(Auras aura) {
		return canStoreTier(aura.getTier());
	}
}
