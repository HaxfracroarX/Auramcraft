package com.auramcraft.tileentity;

import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import com.auramcraft.reference.Names;
import com.auramcraft.reference.Tiers;
import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.api.IAuraUser;
import com.auramcraft.block.StorageJar;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.item.AuraItem;

public class TileStorageJar extends TileAuramcraftAuraUser {
	public TileStorageJar() {
		super(Names.Blocks.STORAGEJAR, AuramcraftBlocks.storageJar, new AuraContainer(32, Tiers.getTotalTiers()));
	}
}
