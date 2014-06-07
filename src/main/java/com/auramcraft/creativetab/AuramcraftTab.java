package com.auramcraft.creativetab;

import com.auramcraft.Auramcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AuramcraftTab extends CreativeTabs {
	public AuramcraftTab(String label) {
		super(label);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Auramcraft.airShard;
	}
}
