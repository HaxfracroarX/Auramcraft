package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class WandCore extends Item {
	public int textureID;
	public String texture;
	
	public WandCore(int textureID, String material) {
		this.textureID = textureID;
		this.texture = Textures.Items.wandCores[textureID];
		
		setTextureName(texture);
		setUnlocalizedName(Names.Items.WAND_CORE + material);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
}
