package com.auramcraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftRecipes {
	public static void init() {
		/*	SHAPED	*/
		// Dev Toy
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.devToy),
				" X ",
				" X ",
				" Y ",
				'X', Blocks.bedrock,
				'Y', Items.stick);
		
		// Holy Lamp
		GameRegistry.addRecipe(new ItemStack(AuramcraftBlocks.holyLamp),
				" X ",
				"XZX",
				"YYY",
				'X', Blocks.glass,
				'Y', AuramcraftItems.gemstone,
				'Z', AuramcraftItems.fireShard);
		
		// Book of Aura
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.bookOfAura),
				" X ",
				"XYX",
				" X ",
				'X', AuramcraftItems.gemstone,
				'Y', Items.book);
		
		// Aura Crystal
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.auraCrystal),
				"XGX",
				"GDG",
				"XGX",
				'X', Blocks.glass,
				'G', Items.gold_ingot,
				'D', Items.diamond);
		
		/*	SHAPELESS	*/
		// Aum Wood Planks
		GameRegistry.addShapelessRecipe(new ItemStack(AuramcraftBlocks.aumwoodPlanks, 4), AuramcraftBlocks.aumwood);
		
		// Sticks
		GameRegistry.addShapelessRecipe(new ItemStack(Items.stick, 4), AuramcraftBlocks.aumwoodPlanks);
		
		/*	SMELTING	*/
		// None
		
		LogHelper.info("Initialized Recipes");
	}
}
