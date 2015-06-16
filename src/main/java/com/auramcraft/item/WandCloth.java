package com.auramcraft.item;

import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import net.minecraft.item.Item;

public class WandCloth extends Item {
	public int textureID;
	public String texture;
	
	public WandCloth(int textureID, String material) {
		this(textureID);
		
		setUnlocalizedName(Names.Items.WAND_CLOTH + material);
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	public WandCloth(int textureID) {
		this.textureID = textureID;
		this.texture = Textures.Items.wandCloths[textureID];
		setTextureName(texture);
	}
}
