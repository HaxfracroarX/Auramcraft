package com.auramcraft.reference;

import com.auramcraft.item.WandCap;
import com.auramcraft.item.WandCloth;
import com.auramcraft.item.WandCore;

public class WandParts {
	public static WandCore 
		coreAuramwood = new WandCore(Textures.Items.WAND_CORE_AURAMWOOD),
		coreDiamond = new WandCore(Textures.Items.WAND_CORE_DIAMOND),
		coreInfused = new WandCore(Textures.Items.WAND_CORE_INFUSED);
	
	public static WandCap 
		capGemstone = new WandCap(Textures.Items.WAND_CAP_GEMSTONE),
		capIron = new WandCap(Textures.Items.WAND_CAP_IRON),
		capGold = new WandCap(Textures.Items.WAND_CAP_GOLD),
		capDiamond = new WandCap(Textures.Items.WAND_CAP_DIAMOND),
		capInfused = new WandCap(Textures.Items.WAND_CAP_INFUSED),
		capMagic = new WandCap(Textures.Items.WAND_CAP_MAGIC);
	
	public static WandCloth
		clothEmpty = new WandCloth(Textures.Items.WAND_EMPTY),
		clothInfused = new WandCloth(Textures.Items.WAND_CLOTH_INFUSED),
		clothMagic = new WandCloth(Textures.Items.WAND_CLOTH_MAGIC);
}
