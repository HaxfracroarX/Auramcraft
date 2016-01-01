package com.auramcraft.init;

import com.auramcraft.api.Auras;
import com.auramcraft.item.*;
import com.auramcraft.item.tools.SacrificialDagger;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

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
		wandPart = new WandPart(),
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
		GameRegistry.registerItem(wandPart, wandPart.getUnlocalizedName());
		GameRegistry.registerItem(wand, wand.getUnlocalizedName());
		
		LogHelper.info("Initialized Items");
	}
}
