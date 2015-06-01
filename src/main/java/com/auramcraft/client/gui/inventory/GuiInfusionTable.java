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
import java.util.Collection;

@SuppressWarnings("unchecked")
public class GuiInfusionTable extends GuiContainer {
	private final TileInfusionTable tileInfusionTable;
	private final int[] slotX = new int[] {
			17, 50, 83, 116, 149
	};
	private final Auras[] auras = Auras.values();
	
	public GuiInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(new ContainerInfusionTable(inventoryPlayer, world, x, y, z));
		this.tileInfusionTable = (TileInfusionTable) world.getTileEntity(x, y, z);
		xSize = 190;
		ySize = 179;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		// Draw Background
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_INFUSION_TABLE);
	    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		// Check if there is an item in the output
		if(tileInfusionTable.getStackInSlot(10) == null)
			return;
		
		// Get infusion recipes
		ArrayList<IInfusionRecipe> recipes = new ArrayList<IInfusionRecipe>((Collection<? extends IInfusionRecipe>) AuramcraftCraftingManager.getInstance().getRecipeList());
		
		// Get the aura needed by the recipe
		ArrayList recipeAuras = new ArrayList();
		
		// Check for matching recipe
		for(IInfusionRecipe recipe : recipes) {
			if(recipe.getRecipeOutput().getItem().equals(tileInfusionTable.getStackInSlot(10).getItem())) {
				recipeAuras = new ArrayList(recipe.getRecipeAuras());
				break;
			}
		}
		
		// Bind Aura textures
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_INFUSION_TABLE_AURAS);
		
		// Draw Aura Buttons
		GL11.glColor4f(1f, 1f, 1f, 1f);
		for(int i = 0; i < 5; i++) {
			if(recipeAuras.contains(Auras.values()[i]))
				drawTexturedModalRect(guiLeft + slotX[i], guiTop + 60, ((auras[i].getTier() - 1) * 32), (auras[i].getId() * 32), 32, 32);
		}
	}
}
