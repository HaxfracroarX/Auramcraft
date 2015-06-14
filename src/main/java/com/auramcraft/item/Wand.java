package com.auramcraft.item;

import com.auramcraft.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Wand extends AuraItem {
	WandCore core;
	WandCap cap;
	WandCloth cloth;
	IIcon coreIcon, capIcon, clothIcon;
	
	public Wand(int maxAura, int tier, WandCore core, WandCap cap, WandCloth cloth) {
		super(maxAura, tier);
		this.core = core;
		this.cap = cap;
		this.cloth = cloth;
		
		setUnlocalizedName(Names.Items.WAND);
		setMaxStackSize(1);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		initNBT(itemStack);
	}
	
	public static void initNBT(ItemStack itemStack) {
		AuraItem.initNBT(itemStack);
		
		Wand wand = (Wand) itemStack.getItem();
		
		itemStack.stackTagCompound.setString("Core", wand.core.texture);
		itemStack.stackTagCompound.setString("Cap", wand.core.texture);
		itemStack.stackTagCompound.setString("Cloth", wand.core.texture);
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
		coreIcon = iconRegister.registerIcon(core.texture);
		capIcon = iconRegister.registerIcon(cap.texture);
		clothIcon = iconRegister.registerIcon(cloth.texture);
    }
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		switch(pass) {
			case 0:
				return coreIcon;
			case 1:
				return capIcon;
			case 2:
				return clothIcon;
		}
		
		return null;
	}
}
