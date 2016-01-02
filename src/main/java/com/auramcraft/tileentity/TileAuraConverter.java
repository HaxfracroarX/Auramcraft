package com.auramcraft.tileentity;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.Auras;
import com.auramcraft.init.AuramcraftBlocks;
import com.auramcraft.item.AuraItem;
import com.auramcraft.network.PacketHandler;
import com.auramcraft.network.message.MsgAuraConverter;
import com.auramcraft.reference.Names;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileAuraConverter extends TileInventory implements IMessageHandler<MsgAuraConverter, IMessage> {
	private int tickCounter = 0;
	private boolean isConverting = false;
	private Auras input;
	private Auras output;
	
	public TileAuraConverter() {
		super(Names.Blocks.AURA_CONVERTER, AuramcraftBlocks.auraConverter, 2);
	}
	
	@Override
	public void updateEntity() {
		ItemStack inputStack = getStackInSlot(0);
		ItemStack outputStack = getStackInSlot(1);
		
		// Can't convert without I/O
		if(inputStack == null || outputStack == null) {
			isConverting = false;
			return;
		}
		
		AuraContainer inputContainer = AuraItem.getAuraContainer(inputStack);
		AuraContainer outputContainer = AuraItem.getAuraContainer(outputStack);
		boolean last = isConverting;
		isConverting = inputContainer.getStoredAura(input) > 0 && outputContainer.canStoreAura(output);
		
		if(last != isConverting)
			tickCounter = 0;
		
		if(isConverting && ++tickCounter >= 40) {
			tickCounter = 0;
			
			// Convert aura
			inputContainer.remove(input, 1);
			outputContainer.store(output, 1);
			
			AuraItem.updateNBT(inputStack, inputContainer);
			AuraItem.updateNBT(outputStack, outputContainer);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		isConverting = nbtTagCompound.getBoolean("isConverting");
		input = Auras.values()[nbtTagCompound.getInteger("Input")];
		output = Auras.values()[nbtTagCompound.getInteger("Output")];
		tickCounter = nbtTagCompound.getInteger("TickCounter");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setBoolean("isConverting", isConverting);
		nbtTagCompound.setInteger("Input", input.ordinal());
		nbtTagCompound.setInteger("Output", output.ordinal());
		nbtTagCompound.setInteger("TickCounter", tickCounter);
		
	}
	
	@Override
	public IMessage onMessage(MsgAuraConverter message, MessageContext ctx) {
		TileAuraConverter tileEntity = (TileAuraConverter) Minecraft.getMinecraft().theWorld
			.getTileEntity(message.x, message.y, message.z);
		tileEntity.setOrientation(message.orientation);
		tileEntity.setInput(message.input);
		tileEntity.setOutput(message.output);
		
		return null;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.wrapper.getPacketFrom(new MsgAuraConverter(this));
	}
	
	public void sendPacket() {
		PacketHandler.wrapper.sendToAll(new MsgAuraConverter(this));
	}
	
	public void setInput(Auras aura) {
		input = aura;
	}
	
	public Auras getInput() {
		return input;
	}
	
	public void setOutput(Auras aura) {
		output = aura;
	}
	
	public Auras getOutput() {
		return output;
	}
	
	public int getTickCounter() {
		return tickCounter;
	}
	
	public boolean isConverting() {
		return isConverting;
	}
}
