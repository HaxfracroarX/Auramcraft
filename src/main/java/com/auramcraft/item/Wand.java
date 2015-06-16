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
	private IIcon[] coreIcons, capIcons, clothIcons;
	
	public Wand() {
		super(1, 1);
		setUnlocalizedName(Names.Items.WAND);
		setMaxStackSize(1);
	}
	
	public static void init(ItemStack itemStack, int maxAura, int tier, Item core, Item cap, Item cloth) {
		AuraContainer container = getAuraContainer(itemStack);
		container.setMaxAura(maxAura);
		container.setTier(tier);
		updateNBT(itemStack, container);
		
		itemStack.stackTagCompound.setInteger("Core", ((WandCore) core).textureID);
		itemStack.stackTagCompound.setInteger("Cap", ((WandCap) cap).textureID);
		itemStack.stackTagCompound.setInteger("Cloth", ((WandCloth) cloth).textureID);
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
		coreIcons = new IIcon[Textures.Items.wandCores.length];
		capIcons = new IIcon[Textures.Items.wandCaps.length];
		clothIcons = new IIcon[Textures.Items.wandCloths.length];
		
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
