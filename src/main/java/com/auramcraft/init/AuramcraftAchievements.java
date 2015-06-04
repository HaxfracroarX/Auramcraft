package com.auramcraft.init;

import com.auramcraft.util.LogHelper;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

import java.util.ArrayList;

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class AuramcraftAchievements {
	public static AchievementPage page;
	
	public static Achievement 
		auraCrystal = new Achievement("achievement.auraCrystal", "auraCrystal", 0, 0, AuramcraftItems.auraCrystal, AchievementList.diamonds),
		infusionTable = new Achievement("achievement.infusionTable", "infusionTable", 2, 0, Item.getItemFromBlock(AuramcraftBlocks.infusionTable), auraCrystal);
	
	public static void init() {
		// List of achievements
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		
		// Register and add achievements
		achievements.add(auraCrystal.registerStat());
		achievements.add(infusionTable.registerStat());
		
		// Make achievement page
		page = new AchievementPage("Auramcraft", achievements.toArray(new Achievement[achievements.size()]));
		
		// Register achievement page
		AchievementPage.registerAchievementPage(page);
		
		LogHelper.info("Initialized Achievements");
	}
}
