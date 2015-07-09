package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Wand extends AuraItem {
	public Wand() {
		super(1, 1);
		setUnlocalizedName(Names.Wand.WAND);
		setMaxStackSize(1);
	}
	
	public static ItemStack updateNBT(ItemStack itemStack, AuraContainer container, int core, int cap, int cloth) {
		AuraItem.updateNBT(itemStack, container);
		
		itemStack.stackTagCompound.setInteger("Core", core);
		itemStack.stackTagCompound.setInteger("Cap", cap);
		itemStack.stackTagCompound.setInteger("Cloth", cloth);
		
		return itemStack;
	}
	
	public static void updateNBT(ItemStack itemStack, AuraContainer container) {
		updateNBT(itemStack, container, getCore(itemStack), getCap(itemStack), getCloth(itemStack));
	}
	
	public static int getCore(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("Core");
	}
	
	public static int getCap(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("Cap");
	}
	
	public static int getCloth(ItemStack itemStack) {
		return itemStack.stackTagCompound.getInteger("Cloth");
	}
	
	@Override
	public int getRenderPasses(int metadata) {
		return 3;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		for(int i = 0; i < Textures.Items.wandCores.length; i++)
			Textures.Items.wandCoreOverlayIcons[i] = iconRegister.registerIcon(Textures.Items.wandCores[i] + "Overlay");
		
		for(int i = 0; i < Textures.Items.wandCaps.length; i++) {
			Textures.Items.wandCapOverlayIcons[i] = iconRegister.registerIcon(Textures.Items.wandCaps[i] + "Overlay");
			Textures.Items.wandCapItemIcons[i] = iconRegister.registerIcon(Textures.Items.wandCaps[i]);
		}
		
		for(int i = 0; i < Textures.Items.wandCloths.length; i++)
			Textures.Items.wandClothOverlayIcons[i] = iconRegister.registerIcon(Textures.Items.wandCloths[i] + "Overlay");
    }
	
	@Override
	public IIcon getIcon(ItemStack itemStack, int pass) {
		switch(pass) {
			case 0:
				return Textures.Items.wandCoreOverlayIcons[getCore(itemStack)];
			case 1:
				return Textures.Items.wandCapOverlayIcons[getCap(itemStack)];
			case 2:
				return Textures.Items.wandClothOverlayIcons[getCloth(itemStack)];
		}
		
		return null;
	}
}
