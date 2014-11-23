package com.auramcraft.client.gui.inventory;

import org.lwjgl.opengl.GL11;
import com.auramcraft.Auramcraft;
import com.auramcraft.api.Auras;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.item.AuraItem;
import com.auramcraft.reference.Reference;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileInfusionTable;
import com.auramcraft.util.LogHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiInfusionTable extends GuiContainer {
	private TileInfusionTable tileInfusionTable;
	private int[] slotX = new int[] {
			17, 51, 85, 117, 150
	};
	private int slotY = 60;
	private Auras[] auras = Auras.values();
	
	public GuiInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(new ContainerInfusionTable(inventoryPlayer, world, x, y, z));
		this.tileInfusionTable = (TileInfusionTable) world.getTileEntity(x, y, z);
		xSize = 190;
		ySize = 179;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
		
		for(int i = 0; i < 5; i++)
			buttonList.add(new AuraButton(i, x+slotX[i], y+slotY, auras[i]));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_INFUSION_TABLE);
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	    
	    mc.getTextureManager().bindTexture(Textures.GUI.GUI_INFUSION_TABLE_AURAS);
	    for(int i = 0; i < 5; i++) {
	    	if(tileInfusionTable.getStackInSlot(9) != null) {
	    		if(AuraItem.getAuraContainer(tileInfusionTable.getStackInSlot(9)).containsAura(auras[i]))
	    			((GuiButton) buttonList.get(i)).visible = true;
	    		else
	    			((GuiButton) buttonList.get(i)).visible = false;
	    	}
	    	else {
	    		if(((GuiButton) buttonList.get(i)).visible)
	    			((GuiButton) buttonList.get(i)).visible = false;
	    	}
	    }
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch(button.id) {
			case 0:
				LogHelper.info("Button 1");
				break;
			case 1:
				LogHelper.info("Button 2");
				break;
			case 2:
				LogHelper.info("Button 3");
				break;
			case 3:
				LogHelper.info("Button 4");
				break;
			case 4:
				LogHelper.info("Button 5");
				break;
		}
	}
}