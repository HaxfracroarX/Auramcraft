package com.auramcraft.init;

import com.auramcraft.api.Auras;
import com.auramcraft.item.*;
import com.auramcraft.item.tools.SacrificialDagger;
import com.auramcraft.reference.Names;
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
		wand = new Wand(25, 2, Textures.Items.WAND_CORE_AURAMWOOD, Textures.Items.WAND_CAP_GEMSTONE, Textures.Items.WAND_EMPTY);
	
	// Creative only
	public static final Item auraCrystalCreative = new AuraCrystal(500);
	
	public static void init() {
		// Creative only
		((AuraCrystal) auraCrystalCreative).getAuraContainer().store(Auras.FIRE, 100, Auras.EARTH, 100, Auras.WATER, 100, Auras.AIR, 100, Auras.AURAM, 100);
		
		GameRegistry.registerItem(fireShard, Names.Items.FIRESHARD);
		GameRegistry.registerItem(earthShard, Names.Items.EARTHSHARD);
		GameRegistry.registerItem(waterShard, Names.Items.WATERSHARD);
		GameRegistry.registerItem(airShard, Names.Items.AIRSHARD);
		GameRegistry.registerItem(auramShard, Names.Items.AURAMSHARD);
		GameRegistry.registerItem(gemstone, Names.Items.GEMSTONE);
		GameRegistry.registerItem(sacrificialDagger, Names.Items.SACRIFICIAL_DAGGER);
		GameRegistry.registerItem(devToy, Names.Items.DEV_TOY);
		GameRegistry.registerItem(bookOfAura, Names.Items.BOOK_OF_AURA);
		GameRegistry.registerItem(auraCrystal, Names.Items.AURACRYSTAL);
		GameRegistry.registerItem(allseeingStone, Names.Items.ALLSEEING_STONE);
		GameRegistry.registerItem(charmOfAllseeing, Names.Items.CHARM_OF_ALLSEEING);
		GameRegistry.registerItem(wand, Names.Items.WAND);
		
		// Creative only
		GameRegistry.registerItem(auraCrystalCreative, Names.Items.AURACRYSTAL + "Creative");
		
		LogHelper.info("Initialized Items");
	}
}
