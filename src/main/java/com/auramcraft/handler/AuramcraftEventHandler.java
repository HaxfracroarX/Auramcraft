package com.auramcraft.handler;

import com.auramcraft.block.AumWoodSapling;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.player.stats.AuramcraftPlayerStats;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.*;

public class AuramcraftEventHandler {
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		World world = event.world;
		
		int x = event.x;
		int y = event.y;
		int z = event.z;
		
		Block block = event.block;
		int meta = world.getBlockMetadata(x, y, z);
		
		if(block instanceof AumWoodSapling) {
			event.setResult(Result.ALLOW);
			
			if(!world.isRemote) {
				double chance = 0.15d;
				
				if(world.rand.nextFloat() < chance)
					((AumWoodSapling)block).func_149878_d(world, x, y, z, event.world.rand);
			}
		}
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
	}
}
