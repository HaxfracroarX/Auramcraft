package com.auramcraft.item;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Wand extends AuraItem {
	private IIcon[] 
		coreIcons = new IIcon[Textures.Items.wandCores.length],
		capIcons = new IIcon[Textures.Items.wandCaps.length],
		clothIcons = new IIcon[Textures.Items.wandCloths.length];
	
	public Wand() {
		super(1, 1);
		setUnlocalizedName(Names.Items.WAND);
		setMaxStackSize(1);
	}
	
	public static ItemStack init(ItemStack itemStack, AuraContainer container, Item core, Item cap, Item cloth) {
		return init(itemStack, container, ((WandCore) core).textureID, ((WandCap) cap).textureID, ((WandCloth) cloth).textureID);
	}
	
	public static ItemStack init(ItemStack itemStack, AuraContainer container, int core, int cap, int cloth) {
		AuraItem.updateNBT(itemStack, container);
		
		itemStack.stackTagCompound.setInteger("Core", core);
		itemStack.stackTagCompound.setInteger("Cap", cap);
		itemStack.stackTagCompound.setInteger("Cloth", cloth);
		
		return itemStack;
	}
	
	public static void updateNBT(ItemStack itemStack, AuraContainer container) {
		init(itemStack, container, getCore(itemStack), getCap(itemStack), getCloth(itemStack));
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
			coreIcons[i] = iconRegister.registerIcon(Textures.Items.wandCores[i]);
		
		for(int i = 0; i < Textures.Items.wandCaps.length; i++)
			capIcons[i] = iconRegister.registerIcon(Textures.Items.wandCaps[i]);
		
		for(int i = 0; i < Textures.Items.wandCloths.length; i++)
			clothIcons[i] = iconRegister.registerIcon(Textures.Items.wandCloths[i]);
    }
	
	@Override
	public IIcon getIcon(ItemStack itemStack, int pass) {
		switch(pass) {
			case 0:
				return coreIcons[getCore(itemStack)];
			case 1:
				return capIcons[getCap(itemStack)];
			case 2:
				return clothIcons[getCloth(itemStack)];
		}
		
		return null;
	}
}
