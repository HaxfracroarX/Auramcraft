package com.auramcraft.item;

import net.minecraft.item.Item;
import com.auramcraft.Auramcraft;
import com.auramcraft.creativetab.CreativeTab;

public abstract class Shard extends Item{
	private int storedAura;
	private int storedMagic;
	private int storedGem;
	private Auras type;
	
	public Shard(Auras type) {
		setStored(8, 5, 5);
		this.type = type;
		this.setCreativeTab(CreativeTab.AuramcraftTab);
	}
	
	public void setStored(int storedAura, int storedMagic, int storedGem) {
		setStoredAura(storedAura);
		setStoredMagic(storedMagic);
		setStoredGem(storedGem);
	}
	
	public void setStoredAura(int storedAura) {
		this.storedAura = storedAura;
	}
	
	public void setStoredMagic(int storedMagic) {
		this.storedMagic = storedMagic;
	}
	
	public void setStoredGem(int storedGem) {
		this.storedGem = storedGem;
	}
	
	public int getStoredAura() {
		return storedAura;
	}
	
	public int getStoredMagic() {
		return storedMagic;
	}
	
	public int getStoredGem() {
		return storedGem;
	}
	
	public Auras getType() {
		return type;
	}
}
