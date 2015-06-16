package com.auramcraft.init;

import com.auramcraft.api.Auras;
import com.auramcraft.item.*;
import com.auramcraft.item.tools.SacrificialDagger;
import com.auramcraft.reference.Textures;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@SuppressWarnings("WeakerAccess")
public class AuramcraftItems {
	public static final Item 
		fireShard = new FireShard(),
		earthShard = new EarthShard(),
		waterShard = new WaterShard(),
		airShard = new AirShard(),
		auramShard = new AuramShard(),
		gemstone = new Gemstone(),
		sacrificialDagger = new SacrificialDagger(),
		devToy = new DevToy(),
		bookOfAura = new BookOfAura(),
		auraCrystal = new AuraCrystal(25),
		allseeingStone = new AllseeingStone(),
		charmOfAllseeing = new CharmOfAllseeing(),
		wandCoreAumwood = new WandCore(Textures.Items.WAND_CORE_AUMWOOD, "Aumwood"),
		wandCoreDiamond = new WandCore(Textures.Items.WAND_CORE_DIAMOND, "Diamond"),
		wandCoreInfused = new WandCore(Textures.Items.WAND_CORE_INFUSED, "Infused"),
		wandCapGemstone = new WandCap(Textures.Items.WAND_CAP_GEMSTONE, "Gemstone"),
		wandCapIron = new WandCap(Textures.Items.WAND_CAP_IRON, "Iron"),
		wandCapGold = new WandCap(Textures.Items.WAND_CAP_GOLD, "Gold"),
		wandCapDiamond = new WandCap(Textures.Items.WAND_CAP_DIAMOND, "Diamond"),
		wandCapInfused = new WandCap(Textures.Items.WAND_CAP_INFUSED, "Infused"),
		wandCapMagic = new WandCap(Textures.Items.WAND_CAP_MAGIC, "Magic"),
		wandClothInfused = new WandCloth(Textures.Items.WAND_CLOTH_INFUSED, "Infused"),
		wandClothMagic = new WandCloth(Textures.Items.WAND_CLOTH_MAGIC, "Magic"),
		wandClothEmpty = new WandCloth(Textures.Items.WAND_EMPTY),
		wand = new Wand();
	
	// Creative only
	public static final Item auraCrystalCreative = new AuraCrystal(500);
	
	public static void init() {
		// Creative only
		((AuraCrystal) auraCrystalCreative).getAuraContainer().store(
			Auras.FIRE, 100,
			Auras.EARTH, 100,
			Auras.WATER, 100,
			Auras.AIR, 100,
			Auras.AURAM, 100);
		GameRegistry.registerItem(auraCrystalCreative, auraCrystalCreative.getUnlocalizedName() + "Creative");
		
		GameRegistry.registerItem(fireShard, fireShard.getUnlocalizedName());
		GameRegistry.registerItem(earthShard, earthShard.getUnlocalizedName());
		GameRegistry.registerItem(waterShard, waterShard.getUnlocalizedName());
		GameRegistry.registerItem(airShard, airShard.getUnlocalizedName());
		GameRegistry.registerItem(auramShard, auramShard.getUnlocalizedName());
		GameRegistry.registerItem(gemstone, gemstone.getUnlocalizedName());
		GameRegistry.registerItem(sacrificialDagger, sacrificialDagger.getUnlocalizedName());
		GameRegistry.registerItem(devToy, devToy.getUnlocalizedName());
		GameRegistry.registerItem(bookOfAura, bookOfAura.getUnlocalizedName());
		GameRegistry.registerItem(auraCrystal, auraCrystal.getUnlocalizedName());
		GameRegistry.registerItem(allseeingStone, allseeingStone.getUnlocalizedName());
		GameRegistry.registerItem(charmOfAllseeing, charmOfAllseeing.getUnlocalizedName());
		GameRegistry.registerItem(wandCoreAumwood, wandCoreAumwood.getUnlocalizedName());
		GameRegistry.registerItem(wandCoreDiamond, wandCoreDiamond.getUnlocalizedName());
		GameRegistry.registerItem(wandCoreInfused, wandCoreInfused.getUnlocalizedName());
		GameRegistry.registerItem(wandCapGemstone, wandCapGemstone.getUnlocalizedName());
		GameRegistry.registerItem(wandCapIron, wandCapIron.getUnlocalizedName());
		GameRegistry.registerItem(wandCapGold, wandCapGold.getUnlocalizedName());
		GameRegistry.registerItem(wandCapDiamond, wandCapDiamond.getUnlocalizedName());
		GameRegistry.registerItem(wandCapInfused, wandCapInfused.getUnlocalizedName());
		GameRegistry.registerItem(wandCapMagic, wandCapMagic.getUnlocalizedName());
		GameRegistry.registerItem(wandClothInfused, wandClothInfused.getUnlocalizedName());
		GameRegistry.registerItem(wandClothMagic, wandClothMagic.getUnlocalizedName());
		GameRegistry.registerItem(wand, wand.getUnlocalizedName());
		
		LogHelper.info("Initialized Items");
	}
}
