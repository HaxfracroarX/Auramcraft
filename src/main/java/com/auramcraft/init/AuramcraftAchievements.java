package com.auramcraft.init;

import java.util.ArrayList;
import com.auramcraft.util.LogHelper;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AuramcraftAchievements {
	public static AchievementPage page;
	
	public static Achievement makeAuraCrystal = new Achievement("achievement.makeAuraCrystal", "makeAuraCrystal", 0, 0, AuramcraftItems.auraCrystal, AchievementList.diamonds);
	
	public static void init() {
		// List of achievements
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		
		// Register and add achievements
		achievements.add(makeAuraCrystal.registerStat());
		
		// Make achievement page
		page = new AchievementPage("Auramcraft", achievements.toArray(new Achievement[achievements.size()]));
		
		// Register achievement page
		AchievementPage.registerAchievementPage(page);
		
		LogHelper.info("Initialized Achievements");
	}
}
