package com.auramcraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.reference.Reference;

public class CreativeTab {
	public static final CreativeTabs AuramcraftTab = new CreativeTabs(Reference.MODID) {
		@Override
		public Item getTabIconItem() {
			return AuramcraftItems.auramShard;
		}
	};
}
