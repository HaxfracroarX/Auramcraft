package com.auramcraft.init;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftRecipes {
	public static void init() {
		/*	SHAPED	*/
		// Dev Toy
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.devToy), new Object[] {
			" X ", 
			" X ", 
			" Y ", 
			'X', Blocks.bedrock,
			'Y', Items.stick
		});
		
		// Holy Lamp
		GameRegistry.addRecipe(new ItemStack(AuramcraftBlocks.holyLamp), new Object[] {
			" X ", 
			"XZX", 
			"YYY", 
			'X', Blocks.glass,
			'Y', AuramcraftItems.gemstone,
			'Z', AuramcraftItems.fireShard
		});
		
		// Book of Aura
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.bookOfAura), new Object[] {
			" X ",
			"XYX",
			" X ",
			'X', AuramcraftItems.gemstone,
			'Y', Items.book
		});
		
		/*	SHAPELESS	*/
		// Aum Wood Planks
		GameRegistry.addShapelessRecipe(new ItemStack(AuramcraftBlocks.aumWoodPlanks, 4), AuramcraftBlocks.aumWood);
		
		// Sticks
		GameRegistry.addShapelessRecipe(new ItemStack(Items.stick, 4), AuramcraftBlocks.aumWoodPlanks);
		
		/*	SMELTING	*/
		// Aura Crystal
		GameRegistry.addSmelting(AuramcraftItems.gemstone, new ItemStack(AuramcraftItems.auraCrystal), 0f);
		
		LogHelper.info("Initialized Recipes");
	}
}
