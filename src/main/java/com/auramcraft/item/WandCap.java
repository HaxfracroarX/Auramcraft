package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class WandCap extends Item {
	public int textureID;
	public String texture;
	
	public WandCap(int textureID, String material) {
		this.textureID = textureID;
		this.texture = Textures.Items.wandCaps[textureID];
		
		setTextureName(texture);
		setUnlocalizedName(Names.Items.WAND_CAP + material);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
