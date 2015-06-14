package com.auramcraft.creativetab;

import com.auramcraft.init.AuramcraftItems;
import com.auramcraft.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {
	public static final CreativeTabs AuramcraftTab = new CreativeTabs(Reference.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			return AuramcraftItems.auramShard;
		}
	};
}
