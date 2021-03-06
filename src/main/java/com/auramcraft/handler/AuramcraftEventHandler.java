package com.auramcraft.handler;

import com.auramcraft.Auramcraft;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.block.AumwoodSapling;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.item.AuraCrystal;
import com.auramcraft.item.Wand;
import com.auramcraft.reference.BookIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.stats.AuramcraftPlayerStats;
import com.auramcraft.util.LogHelper;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class AuramcraftEventHandler extends Gui {
	private final Minecraft mc;
	private int wandTickCounter = 100;
	
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
			int tabID = BookIds.getID(BookIds.artifacts);
			int pageID = BookIds.pageAuraCrystal.getID();
			boolean[] pages = stats.getPages(tabID);
			boolean firstTime = !pages[pageID];
			
			pages[pageID] = true;
			stats.setPages(tabID, pages);
			
			if(firstTime)
				stats.initPageAnnouncement("Aura Crystal");
		}
	}
	
	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
		if(event.type != RenderGameOverlayEvent.ElementType.TEXT)
			return;
		
		// Draw Internal Aura
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(mc.thePlayer);
		AuraContainer container = stats.getAuraContainer();
		
		if(container != null && mc.thePlayer.inventory.hasItem(AuramcraftItems.charmOfAllseeing)) {
			Auras[] allowedAuras = container.getAllowed();
			
			for(int i = 0; i < allowedAuras.length; i++)
				mc.fontRenderer.drawStringWithShadow(allowedAuras[i] + ": " + container.getStoredAura(allowedAuras[i]), 10, 10 * (i + 1), 0x33CCFF);
		}
		// Draw Page Announcement
		else if(stats.isAnnouncing()) {
			String announcement = stats.getAnnouncement();
			
			mc.getTextureManager().bindTexture(Textures.GUI.PAGE_GET);
			
			// Stuff to render transparency
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			
			drawTexturedModalRect(event.resolution.getScaledWidth()*2 - 37*2, 5*2, 0, 0, 64, 64);
			
			GL11.glScalef(2f, 2f, 2f);
			
			// More stuff to render transparency
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			
			mc.fontRenderer.drawStringWithShadow(announcement, event.resolution.getScaledWidth() - mc.fontRenderer.getStringWidth(announcement) - 40, 5, 0x47AA17);
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(!(event.entity instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer) event.entity;
		
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
		
		// Decrement page announcement
		float pageTime = stats.getPageTime();
		
		// Take off 1 tick (0.05 sec) from the timer
		if(stats.isAnnouncing())
			stats.setPageTime(pageTime - 0.05f);
		// If the timer is done, stop announcing
		if(pageTime <= 0 && pageTime > -2) {
			stats.setAnnouncing(false);
			stats.setPageTime(-2);
		}
		
		if(event.entity.worldObj.isRemote)
			return;
		
		// Passive Internal Aura Regen
		AuramcraftPlayerStats.update(player);
		
		// Passive Wand Aura Regen
		if(wandTickCounter-- < 0) {
			for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
				ItemStack itemStack = player.inventory.getStackInSlot(i);
				
				if(itemStack != null && itemStack.getItem() == AuramcraftItems.wand) {
					AuraContainer container = Wand.getAuraContainer(itemStack);
					
					// Store 1 of each type, if it can hold it
					for(Auras aura : Auras.values())
						container.store(aura, 1);
					
					Wand.updateNBT(itemStack, container);
				}
			}
			
			wandTickCounter = 100;
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		if(event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;
		
		// Temporarily store playerData
		NBTTagCompound playerData = new NBTTagCompound();
		AuramcraftPlayerStats.get((EntityPlayer) event.entity).saveNBTData(playerData);
		Auramcraft.proxy.storeEntityData((EntityPlayer) event.entity, playerData);
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
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
			int maxAura = 25;
			Auras allowedAura = Auras.values()[random.nextInt(5)];
			
			// Setup container and fill it up
			AuraContainer container = new AuraContainer(maxAura, 1);
			container.addAllowed(allowedAura);
			container.store(allowedAura, maxAura);
			
			// Give the player the container
			stats.setAuraContainer(container);
			
			// Announce affinity
			player.addChatMessage(new ChatComponentText(player.getDisplayName() + " has been blessed with an affinity for " + allowedAura));
		}
		
		// Send Client a packet
		stats.sendPacket(player);
	}
}
