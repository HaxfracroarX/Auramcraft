package com.auramcraft.inventory;

import com.auramcraft.api.AuraContainer;
import com.auramcraft.api.IAuraContainer;
import com.auramcraft.item.AuraItem;
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
	public InfusionCrafting craftMatrix;
    public IInventory craftResult;
    private SyncedInventory auraItem;
	private World worldObj;
	private int x, y, z;
	private TileEntityInfusionTable tileEntityInfusionTable;
	
	public ContainerInfusionTable(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.tileEntityInfusionTable = (TileEntityInfusionTable) world.getTileEntity(x, y, z);
		this.worldObj = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
		// Crafting grid save/load
		craftMatrix = new InfusionCrafting(this, 3, 3, 25, 1) {
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
		
		// Result save/load
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
		
		// Aura Item
		auraItem = new SyncedInventory(tileEntityInfusionTable);
		
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
		
		// Add the AuraItem
		addSlotToContainer(new Slot(auraItem, 9, 156, 24) {
			public boolean isItemValid(ItemStack itemStack) {
		        return itemStack.getItem() instanceof AuraItem;
		    }
			
			public void onSlotChanged() {
				AuraItem item = (AuraItem) getStack().getItem();
				if(item != null)
					craftMatrix.setAuraContainer(item.getAuraContainer());
				else
					craftMatrix.setAuraContainer(new AuraContainer(25, 1));
				onCraftMatrixChanged(craftMatrix);
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
	
	public boolean interfacesIAuraContainer(Object object) {
		Class[] classes = object.getClass().getInterfaces();
		
		for(int i = 0; i < classes.length; i++) {
			if(classes[i].isAssignableFrom(IAuraContainer.class))
				return true;
		}
		
		return false;
	}
}
