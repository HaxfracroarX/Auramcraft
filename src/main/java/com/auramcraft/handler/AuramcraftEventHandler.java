package com.auramcraft.handler;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.block.AumwoodSapling;
import com.auramcraft.init.AuramcraftAchievements;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;

import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class AuramcraftEventHandler {
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		World world = event.world;
		
		int x = event.x;
		int y = event.y;
		int z = event.z;
		
		Block block = event.block;
		
		if(block instanceof AumwoodSapling) {
			event.setResult(Result.ALLOW);
			
			if(!world.isRemote) {
				double chance = 0.15d;
				
				if(world.rand.nextFloat() < chance)
					((AumwoodSapling)block).func_149878_d(world, x, y, z, event.world.rand);
			}
		}
	}
	
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event) {
		// Aura Crystal Achievement
		if(event.crafting.getItem() == AuramcraftItems.auraCrystal)
			event.player.addStat(AuramcraftAchievements.auraCrystal, 1);
	}
	
	@SubscribeEvent
	public void PlayerLoggedInEvent(PlayerLoggedInEvent event) {
		onPlayerLogin(event.player);
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		onPlayerLogin(event.player);
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityEvent.EntityConstructing event) {
		if(event.entity instanceof EntityPlayer && AuramcraftPlayerStats.get((EntityPlayer) event.entity) == null) {
			AuramcraftPlayerStats.register((EntityPlayer) event.entity);
		}
	}
	
	public void onPlayerLogin(EntityPlayer player) {
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
		
		if(!stats.gotBook()) {
			stats.setBook(true);
			ItemStack book = new ItemStack(AuramcraftItems.bookOfAura);
			
			// Tries to add item to inventory
			if(!player.inventory.addItemStackToInventory(book) && !player.worldObj.isRemote) {
				// Throws it on the ground if inventory is full
				EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, book);
				player.worldObj.spawnEntityInWorld(entityitem);
				if (!(player instanceof FakePlayer))
					entityitem.onCollideWithPlayer(player);
			}
			LogHelper.info("Gave Book of Aura to " + player.getDisplayName());
		}
		
		if(stats.getAuraContainer() == null) {
			// Setup variables for new AuraContainer
			Random random = new Random();
			int maxAura = random.nextInt(11) + 5;
			Auras allowedAura = Auras.values()[random.nextInt(5)];
			
			// Setup container and fill it up
			AuraContainer container = new AuraContainer(maxAura, 1);
			container.addAllowed(allowedAura);
			container.store(allowedAura, maxAura);
			
			// Give the player the container
			stats.setAuraContainer(container);
			
			// Announce affinity
			String announcement = player.getDisplayName() + " has been blessed with an affinity for " + allowedAura;
			
			LogHelper.info(announcement);
			player.addChatMessage(new ChatComponentText(announcement));
		}
	}
}
