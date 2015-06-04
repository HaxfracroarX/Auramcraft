package com.auramcraft.handler;

import com.auramcraft.Auramcraft;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.block.AumwoodSapling;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.item.AuraCrystal;
import com.auramcraft.reference.PageIds;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;

import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class AuramcraftEventHandler {
	private final Minecraft mc;
	
	public AuramcraftEventHandler() {
		mc = Minecraft.getMinecraft();
	}
	
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		if(event.block instanceof AumwoodSapling) {
			event.setResult(Result.ALLOW);
			
			if(!event.world.isRemote) {
				double chance = 0.15d;
				
				if(event.world.rand.nextFloat() < chance)
					((AumwoodSapling) event.block).func_149878_d(event.world, event.x, event.y, event.z, event.world.rand);
			}
		}
	}
	
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event) {
		// Aura Crystal Page
		if(event.crafting.getItem() instanceof AuraCrystal) {
			AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(event.player);
			boolean[] pages = stats.getPages();
			pages[PageIds.AURACRYSTAL] = true;
			stats.setPages(pages);
		}
	}
	
	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
		if(event.type != RenderGameOverlayEvent.ElementType.TEXT)
			return;
		
		AuraContainer container = AuramcraftPlayerStats.get(mc.thePlayer).getAuraContainer();
		
		if(container == null || !mc.thePlayer.inventory.hasItem(AuramcraftItems.charmOfAllseeing))
			return;
		
		Auras allowedAura = container.getAllowed()[0];
		
		mc.fontRenderer.drawStringWithShadow(allowedAura + ": " + container.getStoredAura(allowedAura), 10, 10, 0x33CCFF);
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if(event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;
		
		// Temporarily store playerData
		NBTTagCompound playerData = new NBTTagCompound();
		AuramcraftPlayerStats.get((EntityPlayer) event.entity).saveNBTData(playerData);
		Auramcraft.proxy.storeEntityData((EntityPlayer) event.entity, playerData);
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
		if(event.entity instanceof EntityPlayer && AuramcraftPlayerStats.get((EntityPlayer) event.entity) == null)
			AuramcraftPlayerStats.register((EntityPlayer) event.entity);
	}
	
	public void onPlayerLogin(EntityPlayer player) {
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
		
		// Check for and resurrect playerData from the dead
		NBTTagCompound playerData = Auramcraft.proxy.getEntityData(player);
		if(playerData != null)
			stats.loadNBTData(playerData);
		
		// Check if the player got a free book yet
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
			
			// Log your generosity
			LogHelper.info("Gave Book of Aura to " + player.getDisplayName());
		}
		
		// Check if the player has been blessed yet
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
		
		// Send Client a packet
		stats.sendPacket(player);
	}
}
