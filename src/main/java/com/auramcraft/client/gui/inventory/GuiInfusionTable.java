package com.auramcraft.client.gui.inventory;

import com.auramcraft.api.Auras;
import com.auramcraft.inventory.ContainerInfusionTable;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.item.crafting.IInfusionRecipe;
import com.auramcraft.reference.Textures;
import com.auramcraft.tileentity.TileInfusionTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class GuiInfusionTable extends GuiContainer {
	private final TileInfusionTable tileInfusionTable;
	private World world;
	private final int[] slotX = new int[] {
			17, 50, 83, 116, 149
	};
	private final Auras[] auras = Auras.values();
	
	public GuiInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(new ContainerInfusionTable(inventoryPlayer, world, x, y, z));
		this.tileInfusionTable = (TileInfusionTable) world.getTileEntity(x, y, z);
		this.world = world;
		xSize = 190;
		ySize = 179;
	}
	
	/**
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mx, int my) {
		// Check if there is an item in the output
		if(tileInfusionTable.getStackInSlot(10) == null)
			return;
		
		// Get infusion recipe
		IInfusionRecipe recipe = AuramcraftCraftingManager.getInstance().findMatchingRecipe(((ContainerInfusionTable) inventorySlots).matrix, world);
		
		if(recipe == null)
			return;
		
		// Get the aura needed by the recipe
		ArrayList recipeAuras = new ArrayList(recipe.getRecipeAuras());
		
		// Color of text
		int textColor = 0x996633;
		
		for(int i = 0; i < 5; i++) {
			if(recipeAuras.contains(auras[i])) {
				String name = auras[i].toString();
				String amount = "x" + recipeAuras.get(recipeAuras.indexOf(auras[i]) + 1);
				
				fontRendererObj.drawString(name, slotX[i] + (32 - fontRendererObj.getStringWidth(name))/2, 60 + (32 - fontRendererObj.FONT_HEIGHT)/2 - fontRendererObj.FONT_HEIGHT/2, textColor);
				fontRendererObj.drawString(amount, slotX[i] + (32 - fontRendererObj.getStringWidth(amount))/2, 60 + (32 - fontRendererObj.FONT_HEIGHT)/2 + fontRendererObj.FONT_HEIGHT/2, textColor);
			}
		}
	}
	
	/**
	 * @param renderPartialTicks Partial ticks from last render
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int mx, int my) {
		// Draw Background
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_INFUSION_TABLE);
	    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
