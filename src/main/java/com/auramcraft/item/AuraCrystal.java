package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.block.GemstoneOre;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.reference.BookIds;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import com.auramcraft.stats.AuramcraftPlayerStats;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class AuraCrystal extends AuraItem {
	public AuraCrystal(int maxAura) {
		super(maxAura, 1);
		setTextureName(Textures.Items.AURA_CRYSTAL);
		setUnlocalizedName(Names.Items.AURACRYSTAL);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if(player.isSneaking()) {
			AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
			AuraContainer itemContainer = AuraItem.getAuraContainer(itemStack);
			AuraContainer container = stats.getAuraContainer();
			Auras[] allowedAuras = container.getAllowed();
			
			for(Auras allowed : allowedAuras) {
				int storedAura = container.getStoredAura(allowed);
				
				if(itemContainer.canStoreAura(allowed, storedAura)) {
					if(!world.isRemote)
						player.addChatMessage(new ChatComponentText("Drained " + storedAura + " " + allowed + " from " + player.getDisplayName() + " into the Aura Crystal"));
				}
				else {
					if(!world.isRemote)
						player.addChatMessage(new ChatComponentText("The Aura Container is full"));
					break;
				}
				
				itemContainer.transfer(container, allowed, storedAura);
			}
			
			AuraItem.updateNBT(itemStack, itemContainer);
		}
		
		return itemStack;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		
		// Transform crafting table into infusion table
		if(block instanceof BlockWorkbench) {
			world.setBlock(x, y, z, AuramcraftBlocks.infusionTable);
			world.getBlock(x, y, z).onBlockPlacedBy(world, x, y, z, player, stack);
			
			// TODO: Add achievement
			
			return true;
		}
		// Shards Page
		else if(block instanceof GemstoneOre) {
			AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
			int tabID = BookIds.getID(BookIds.misc);
			int pageID = BookIds.pageShards.getID();
			boolean[] pages = stats.getPages(tabID);
			boolean firstTime = !pages[pageID];
			
			pages[pageID] = true;
			stats.setPages(tabID, pages);
			
			if(firstTime)
				stats.initPageAnnouncement("Shards");
			
			return firstTime;
		}
		
		return false;
	}
}
