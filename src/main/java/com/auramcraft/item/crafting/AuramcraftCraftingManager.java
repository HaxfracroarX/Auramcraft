package com.auramcraft.item.crafting;

import com.auramcraft.api.Auras;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.inventory.InfusionCrafting;
import com.auramcraft.item.WandPart;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import com.auramcraft.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"unchecked", "WeakerAccess", "CanBeFinal", "UnusedReturnValue"})
public class AuramcraftCraftingManager {
	private static final AuramcraftCraftingManager instance = new AuramcraftCraftingManager();
	private List recipes = new ArrayList();
	
	private AuramcraftCraftingManager() {
		/* SHAPED */
		// Charm of Allseeing
		addRecipe(new ItemStack(AuramcraftItems.charmOfAllseeing), new Object[] {
			"GGG",
			"GAG",
			"GGG",
			'G', Items.gold_ingot,
			'A', AuramcraftItems.allseeingStone
		}, new Object[] {
			Auras.FIRE, 75,
			Auras.WATER, 75,
			Auras.AURAM, 100
		});
		
		// Fire Shard
		addRecipe(new ItemStack(AuramcraftItems.fireShard, 5), new Object[] {
			"GG ",
			" G ",
			" GG",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.FIRE, 40
		});
		
		// Earth Shard
		addRecipe(new ItemStack(AuramcraftItems.earthShard, 5), new Object[] {
			"GGG",
			"   ",
			"G G",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.EARTH, 40
		});
		
		// Water Shard
		addRecipe(new ItemStack(AuramcraftItems.waterShard, 5), new Object[] {
			" G ",
			"G G",
			"G G",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.WATER, 40
		});
		
		// Air Shard
		addRecipe(new ItemStack(AuramcraftItems.airShard, 5), new Object[] {
			"G G",
			" G ",
			"G G",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.AIR, 40
		});
		
		// Auram Shard
		addRecipe(new ItemStack(AuramcraftItems.auramShard, 5), new Object[] {
			" G ",
			"GGG",
			" G ",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.AURAM, 40
		});
		
		// Aumwood Wand Core
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart), 
			Names.Wand.WAND_CORE, 
			Textures.Items.WAND_CORE_AUMWOOD,
			Names.Wand.AUMWOOD,
			0,
			1
		), new Object[] {
			" W",
			"W ",
			'W', AuramcraftBlocks.aumwood
		}, new Object[] {
			Auras.EARTH, 50
		});
		
		// Diamond Wand Core
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CORE,
			Textures.Items.WAND_CORE_DIAMOND,
			Names.Wand.DIAMOND,
			0,
			3
		), new Object[] {
			" D",
			"D ",
			'D', Items.diamond
		}, new Object[] {
			Auras.EARTH, 100
		});
		
		// Gemstone Wand Cap
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CAP,
			Textures.Items.WAND_CAP_GEMSTONE,
			Names.Wand.GEMSTONE,
			100,
			0
		), new Object[] {
			"G",
			'G', AuramcraftItems.gemstone
		}, new Object[] {
			Auras.AURAM, 50
		});
		
		// Iron Wand Cap
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CAP,
			Textures.Items.WAND_CAP_IRON,
			Names.Wand.IRON,
			200,
			0
		), new Object[] {
			"I",
			'I', Items.iron_ingot
		}, new Object[] {
			Auras.AURAM, 65
		});
		
		// Gold Wand Cap
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CAP,
			Textures.Items.WAND_CAP_GOLD,
			Names.Wand.GOLD,
			300,
			0
		), new Object[] {
			"G",
			'G', Items.gold_ingot
		}, new Object[] {
			Auras.AURAM, 80
		});
		
		// Diamond Wand Cap
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CAP,
			Textures.Items.WAND_CAP_DIAMOND,
			Names.Wand.DIAMOND,
			400,
			0
		), new Object[] {
			"D",
			'D', Items.diamond
		}, new Object[] {
			Auras.AURAM, 100
		});
		
		// Infused Wand Cloth
		ItemStack wandClothInfused = WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CLOTH,
			Textures.Items.WAND_CLOTH_INFUSED,
			Names.Wand.INFUSED,
			0,
			0
		);
		addRecipe(wandClothInfused, new Object[] {
			"W",
			'W', Blocks.wool
		}, new Object[] {
			Auras.MAGIC, 100
		});
		
		// Magic Wand Cloth
		addRecipe(WandPart.updateNBT(
			new ItemStack(AuramcraftItems.wandPart),
			Names.Wand.WAND_CLOTH,
			Textures.Items.WAND_CLOTH_MAGIC,
			Names.Wand.MAGIC,
			0,
			0
		), new Object[] {
			"C",
			'C', wandClothInfused
		}, new Object[] {
			Auras.MAGIC, 200
		});
		
		// Wand
		addWandRecipe(
			new ItemStack(AuramcraftItems.wand),
			new Object[] {"  C", " A ", "C  ", 'C', AuramcraftItems.wandPart, 'A', AuramcraftItems.wandPart},
			new Object[] {Auras.AIR, 50, Auras.AURAM, 50, Auras.EARTH, 50, Auras.FIRE, 50, Auras.WATER, 50}
		);
		
		/* SHAPELESS */
		// Allseeing Stone
		addShapelessRecipe(new ItemStack(AuramcraftItems.allseeingStone), new Object[] {
			Blocks.stone
		}, new Object[] {
			Auras.WATER, 50,
			Auras.AURAM, 25
		});
		
		// Wand with cloth
		addWandShapelessRecipe(new ItemStack(AuramcraftItems.wand), new Object[] {
			AuramcraftItems.wand, AuramcraftItems.wandPart
		}, new Object[] {
			Auras.MAGIC, 50
		});
		
		Collections.sort(recipes, new AuramcraftRecipeSorter(this));
		
		LogHelper.info("Infusion Recipes Initialized");
	}
	
	public static AuramcraftCraftingManager getInstance() {
		return instance;
	}
	
	public InfusionWandShapedRecipe addWandRecipe(ItemStack itemStack, Object[] items, Object[] auras) {
		ArrayList itemList = new ArrayList();
		ArrayList auraList = new ArrayList();
		
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		
		if(items[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) items[i++]);

			for(String s1 : astring) {
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else {
			while(items[i] instanceof String) {
				String s2 = (String) items[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		
		HashMap hashmap;
		
		for(hashmap = new HashMap(); i < items.length; i += 2) {
			Character character = (Character) items[i];
			ItemStack itemstack1 = null;
			
			if(items[i + 1] instanceof Item)
				itemstack1 = new ItemStack((Item) items[i + 1]);
			else if(items[i + 1] instanceof Block)
				itemstack1 = new ItemStack((Block) items[i + 1], 1, 32767);
			else if(items[i + 1] instanceof ItemStack)
				itemstack1 = (ItemStack) items[i + 1];
			
			hashmap.put(character, itemstack1);
		}
		
		ItemStack[] aitemstack = new ItemStack[j * k];
		
		for(int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);
			
			if(hashmap.containsKey(Character.valueOf(c0)))
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			else
				aitemstack[i1] = null;
		}

		Collections.addAll(itemList, aitemstack);

		Collections.addAll(auraList, auras);
		
		InfusionWandShapedRecipe shapedrecipes = new InfusionWandShapedRecipe(j, k, itemList, itemStack, auraList);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}
	
	public InfusionShapedRecipe addRecipe(ItemStack itemStack, Object[] items, Object[] auras) {
		ArrayList itemList = new ArrayList();
		ArrayList auraList = new ArrayList();
		
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		
		if(items[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) items[i++]);

			for(String s1 : astring) {
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else {
			while(items[i] instanceof String) {
				String s2 = (String) items[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		
		HashMap hashmap;
		
		for(hashmap = new HashMap(); i < items.length; i += 2) {
			Character character = (Character) items[i];
			ItemStack itemstack1 = null;
			
			if(items[i + 1] instanceof Item)
				itemstack1 = new ItemStack((Item) items[i + 1]);
			else if(items[i + 1] instanceof Block)
				itemstack1 = new ItemStack((Block) items[i + 1], 1, 32767);
			else if(items[i + 1] instanceof ItemStack)
				itemstack1 = (ItemStack) items[i + 1];
			
			hashmap.put(character, itemstack1);
		}
		
		ItemStack[] aitemstack = new ItemStack[j * k];
		
		for(int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);
			
			if(hashmap.containsKey(Character.valueOf(c0)))
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			else
				aitemstack[i1] = null;
		}

		Collections.addAll(itemList, aitemstack);

		Collections.addAll(auraList, auras);
		
		InfusionShapedRecipe shapedrecipes = new InfusionShapedRecipe(j, k, itemList, itemStack, auraList);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}
	
	public void addWandShapelessRecipe(ItemStack itemStack, Object[] items, Object[] auras) {
		ArrayList itemList = new ArrayList();
		ArrayList auraList = new ArrayList();

		for(Object item : items) {
			if(item instanceof ItemStack)
				itemList.add(((ItemStack) item).copy());
			else if(item instanceof Item)
				itemList.add(new ItemStack((Item) item));
			else {
				if(!(item instanceof Block))
					throw new RuntimeException("Invalid shapeless recipe!");

				itemList.add(new ItemStack((Block) item));
			}
		}

		Collections.addAll(auraList, auras);
		
		recipes.add(new InfusionWandShapelessRecipe(itemStack, itemList, auraList));
	}
	
	public void addShapelessRecipe(ItemStack itemStack, Object[] items, Object[] auras) {
		ArrayList itemList = new ArrayList();
		ArrayList auraList = new ArrayList();

		for(Object item : items) {
			if(item instanceof ItemStack)
				itemList.add(((ItemStack) item).copy());
			else if(item instanceof Item)
				itemList.add(new ItemStack((Item) item));
			else {
				if(!(item instanceof Block))
					throw new RuntimeException("Invalid shapeless recipe!");

				itemList.add(new ItemStack((Block) item));
			}
		}

		Collections.addAll(auraList, auras);
		
		recipes.add(new InfusionShapelessRecipe(itemStack, itemList, auraList));
	}
	
	public ItemStack findMatchingRecipeItem(InfusionCrafting crafting, World world) {
		IInfusionRecipe recipe = findMatchingRecipe(crafting, world);
		return recipe != null ? recipe.getCraftingResult(crafting) : null;
	}
	
	public IInfusionRecipe findMatchingRecipe(InfusionCrafting crafting, World world) {
		for(IInfusionRecipe recipe : (List<IInfusionRecipe>) recipes) {
			if(recipe.matches(crafting, world))
				return recipe;
		}
		
		return null;
	}
	
	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return recipes;
	}
}
