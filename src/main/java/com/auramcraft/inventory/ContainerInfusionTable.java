package com.auramcraft.inventory;

import com.auramcraft.item.IAuraContainer;
import com.auramcraft.item.crafting.AuramcraftCraftingManager;
import com.auramcraft.tileentity.TileEntityInfusionTable;
import com.auramcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerInfusionTable extends Container {
	public InventoryCrafting craftMatrix;
    public IInventory craftResult;
	private World worldObj;
	private int x, y, z;
	private TileEntityInfusionTable tileEntityInfusionTable;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		this.x = x;
		this.y = y;
		this.z = z;
		craftMatrix = new InventoryCrafting(this, 3, 3) {
			@Override
			public void openInventory() {
				for(int i = 0; i < 9; i++)
					setInventorySlotContents(i, tileEntityInfusionTable.getStackInSlot(i));
			}
			
			@Override
		    public void closeInventory() {
				for(int i = 0; i < 9; i++)
					tileEntityInfusionTable.setInventorySlotContents(i, getStackInSlot(i));
			}
		};
		craftResult = new InventoryCraftResult() {
			@Override
			public void openInventory() {
				setInventorySlotContents(0, tileEntityInfusionTable.getStackInSlot(10));
			}
			
			@Override
		    public void closeInventory() {
				tileEntityInfusionTable.setInventorySlotContents(10, getStackInSlot(0));
			}
		};
		tileEntityInfusionTable.openInventory();
		craftMatrix.openInventory();
		
		// Add Output slot
		addSlotToContainer(new SlotCrafting(inventoryPlayer.player, craftMatrix, craftResult, 0, 102, 24));
		
		// Add Infusion Table crafting slots
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(craftMatrix, j + i * 3, i * 19 + 6, (3 - j) + j * 20));
			}
		}
		
		// Add the IAuraContainer slot
		addSlotToContainer(new Slot(tileEntityInfusionTable, 9, 156, 24) {
			public boolean isItemValid(ItemStack itemStack) {
		        return itemStack.getItem() instanceof IAuraContainer;
		    }
		});
		
		// Add the Player's inventory
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + j * 19, 96 + i * 19));
            }
        }
		
		// Add the action slots
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 11 + i * 19, 158));
        
        onCraftMatrixChanged(craftMatrix);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		craftResult.setInventorySlotContents(0, AuramcraftCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot) {
		return null;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		craftMatrix.closeInventory();
		tileEntityInfusionTable.closeInventory();
	}
}
