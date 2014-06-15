package com.auramcraft.item;

import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class DevToy extends Item {
	
	public DevToy() {
		setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer Entityplayer) {

	Vec3 look = Entityplayer.getLookVec();
	MovingObjectPosition Coord = Entityplayer.rayTrace(300, 1);	
	         EntityLightningBolt Lightning = new EntityLightningBolt(world, 1, 1, 1);
	         Lightning.setPosition(Coord.blockX,Coord.blockY,Coord.blockZ);
	         world.spawnEntityInWorld(Lightning);
			return itemStack;
	}
}

