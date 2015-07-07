package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.item.AuraItem;
import com.auramcraft.reference.Names;
import net.minecraft.item.ItemStack;

public class TileAuraConverter extends TileAuramcraftAuraUserInventory {
	private int tickCounter = 0;
	private Auras input = Auras.AIR;
	private Auras output = Auras.AIR;
	
	public TileAuraConverter() {
		super(Names.Blocks.AURA_CONVERTER, AuramcraftBlocks.auraConverter, 2, new AuraContainer(100, 1));
	}
	
	public void setInput(Auras aura) {
		input = aura;
	}
	
	public void setOutput(Auras aura) {
		output = aura;
	}
	
	public void transfer(ItemStack itemStack) {
		if(itemStack == null)
			return;
		
		AuraContainer container = getAuraContainer();
		AuraContainer itemContainer = AuraItem.getAuraContainer(itemStack);
		
		itemContainer.transfer(container, output, container.getStoredAura(output));
		
		setAuraContainer(container);
		AuraItem.updateNBT(itemStack, itemContainer);
	}
	
	@Override
	public void updateEntity() {
		if(++tickCounter >= 40) {
			tickCounter = 0;
			AuraContainer container = getAuraContainer();
			
			// Convert aura
			if(container.getStoredAura(input) > 0) {
				container.remove(input, 1);
				container.store(output, 1);
			}
			
			// Move aura to itemStack
			transfer(getStackInSlot(1));
			
			setAuraContainer(container);
		}
	}
}
