package com.auramcraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class AuramcraftRecipes {
	public static void init() {
	
	//SHAPED	
		//Dev Toy
		GameRegistry.addRecipe(new ItemStack(AuramcraftItems.devToy, 1), new Object[] {
			" X ", 
			" X ", 
			" Y ", 
			'X', Blocks.bedrock, 'Y', Items.stick});
		
	//SHAPELESS
		//Aum Wood Planks
		GameRegistry.addShapelessRecipe(new ItemStack(AuramcraftBlocks.aumWoodPlanks, 4), AuramcraftBlocks.aumWood);
		
	LogHelper.info("Recipes Initialized");
	}
}
