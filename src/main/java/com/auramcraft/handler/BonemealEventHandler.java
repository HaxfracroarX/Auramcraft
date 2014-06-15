package com.auramcraft.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import com.auramcraft.block.*;

public class BonemealEventHandler {
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		World world = event.world;
		
		int x = event.x;
		int y = event.y;
		int z = event.z;
		
		Block block = event.block;
		int meta = world.getBlockMetadata(x, y, z);
		
		if(block instanceof AumWoodSapling) {
			event.setResult(Result.ALLOW);
			
			if(!world.isRemote) {
				double chance = 0.15d;
				
				if(world.rand.nextFloat() < chance)
					((AumWoodSapling)block).func_149878_d(world, x, y, z, event.world.rand);
			}
		}
	}
}
