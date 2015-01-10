package com.auramcraft.init;

import net.minecraft.item.Item;
import com.auramcraft.api.Auras;
import com.auramcraft.item.*;
import com.auramcraft.item.tools.*;
import com.auramcraft.reference.Names;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

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
		charmOfAllseeing = new CharmOfAllseeing();
	
	// Creative only
	public static Item auraCrystal1 = new AuraCrystal(500);
	
	public static void init() {
		// Creative only
		((AuraCrystal) auraCrystal1).getAuraContainer().store(new Object[] {
				Auras.FIRE, 100,
				Auras.EARTH, 100,
				Auras.WATER, 100,
				Auras.AIR, 100,
				Auras.AURAM, 100
		});
		
		GameRegistry.registerItem(fireShard, Names.Items.FIRESHARD);
		GameRegistry.registerItem(earthShard, Names.Items.EARTHSHARD);
		GameRegistry.registerItem(waterShard, Names.Items.WATERSHARD);
		GameRegistry.registerItem(airShard, Names.Items.AIRSHARD);
		GameRegistry.registerItem(auramShard, Names.Items.AURAMSHARD);
		GameRegistry.registerItem(gemstone, Names.Items.GEMSTONE);
		GameRegistry.registerItem(sacrificialDagger, Names.Items.SACRIFICIALDAGGER);
		GameRegistry.registerItem(devToy, Names.Items.DEVTOY);
		GameRegistry.registerItem(bookOfAura, Names.Items.BOOK_OF_AURA);
		GameRegistry.registerItem(auraCrystal, Names.Items.AURACRYSTAL);
		GameRegistry.registerItem(allseeingStone, Names.Items.ALLSEEING_STONE);
		GameRegistry.registerItem(charmOfAllseeing, Names.Items.CHARM_OF_ALLSEEING);
		
		// Creative only
		GameRegistry.registerItem(auraCrystal1, Names.Items.AURACRYSTAL + "1");
		
		LogHelper.info("Initialized Items");
	}
}
