package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.GUIIds;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BookOfAura extends Item {
	public BookOfAura() {
		super();
		setUnlocalizedName(Names.Items.BOOK_OF_AURA);
		setCreativeTab(CreativeTab.AuramcraftTab);
		setTextureName(Textures.Items.ITEM_BOOK_OF_AURA);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(Auramcraft.instance, GUIIds.BOOK_OF_AURA, world, 0, 0, 0);
		return stack;
	}
	
	
}
