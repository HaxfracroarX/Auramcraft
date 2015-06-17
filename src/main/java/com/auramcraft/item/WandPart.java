package com.auramcraft.item;

import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class WandPart extends Item {
	public WandPart() {
		setUnlocalizedName(Names.Wand.WAND_PART);
		setMaxStackSize(1);
	}
	
	public static ItemStack updateNBT(ItemStack itemStack, String part, int textureID, String material, int maxAura, int tier) {
		itemStack.stackTagCompound = new NBTTagCompound();
		
		itemStack.stackTagCompound.setString("WandPart", part);
		itemStack.stackTagCompound.setInteger("TextureID", textureID);
		itemStack.stackTagCompound.setString("Material", material);
		itemStack.stackTagCompound.setInteger("MaxAura", maxAura);
		itemStack.stackTagCompound.setInteger("Tier", tier);
		
		String transWand = StatCollector.translateToLocal(Names.Wand.WAND);
		String transPart = StatCollector.translateToLocal(part);
		String transMat = StatCollector.translateToLocal(material);
		
		itemStack.setStackDisplayName(transMat + " " + transWand + " " + transPart);
		
		return itemStack;
	}
	
	public static String getPart(ItemStack itemStack) {
		return itemStack.stackTagCompound.getString("WandPart");
	}
	
	public static int getTextureID(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("TextureID");
	}
	
	public static String getMaterial(ItemStack itemStack) {
		return itemStack.stackTagCompound.getString("Material");
	}
	
	public static int getMaxAura(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("MaxAura");
	}
	
	public static int getTier(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("Tier");
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		// Needed to stop missing texture
	}
	
	@Override
	public IIcon getIcon(ItemStack itemStack, int pass) {
		String part = getPart(itemStack);
		
		if(part.equals("Core"))
			return Textures.Items.wandCoreIcons[getTextureID(itemStack)];
		if(part.equals("Cap"))
			return Textures.Items.wandCapIcons[getTextureID(itemStack)];
		if(part.equals("Cloth"))
			return Textures.Items.wandClothIcons[getTextureID(itemStack)];
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack itemStack) {
		return getIcon(itemStack, 0);
	}
}
