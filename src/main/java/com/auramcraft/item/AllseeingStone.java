package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import com.auramcraft.stats.AuramcraftPlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class AllseeingStone extends Item {
	public AllseeingStone() {
		setTextureName(Textures.Items.ITEM_ALLSEEING_STONE);
		setUnlocalizedName(Names.Items.ALLSEEING_STONE);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if(player.isSneaking() && !world.isRemote) {
			AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(player);
			AuraContainer container = stats.getAuraContainer();
			Auras allowedAura = container.getAllowed()[0];
			int storedAura = container.getStoredAura(allowedAura);
			
			player.addChatMessage(new ChatComponentText("You currently have " + storedAura + " " + allowedAura));
		}
		
		return itemStack;
	}
}
