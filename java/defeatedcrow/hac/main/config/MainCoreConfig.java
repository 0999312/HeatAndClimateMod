package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainCoreConfig {

	private MainCoreConfig() {
	}

	public static final MainCoreConfig INSTANCE = new MainCoreConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean enableAdvHUD = true;
	public static int iconX = 0;
	public static int iconY = -48;

	public static double rateVsRF = 10.0D;
	public static double rateVsEU = 2.0D;
	public static double rateVsFU = 10.0D;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("render setting", "This setting is for such as display and model.");
			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin with the other mods.");

			Property hud_icon = cfg.get("render setting", "Advanced Info on HUD", enableAdvHUD,
					"Enable display the advanced info on HUD.");

			Property hud_x = cfg.get("render setting", "Advanced Info Offset X", iconX,
					"Set the amount of Xoffset of the advanced info.");

			Property hud_y = cfg.get("render setting", "Advanced Info Offset Y", iconY,
					"Set the amount of Yoffset of the advanced info.");

			Property vsRF = cfg.get("plugin setting", "Conversion rate vs RF", rateVsRF,
					"Set the amount of conversion rate as RF/torque.");

			Property vsEU = cfg.get("plugin setting", "Conversion rate vs EU", rateVsEU,
					"Set the amount of conversion rate as EU/torque.");

			Property vsFU = cfg.get("plugin setting", "Conversion rate vs FU", rateVsFU,
					"Set the amount of conversion rate as FU/torque.");

			enableAdvHUD = hud_icon.getBoolean();
			iconX = hud_x.getInt();
			iconY = hud_y.getInt();

			rateVsRF = vsRF.getDouble();
			rateVsEU = vsEU.getDouble();
			rateVsFU = vsFU.getDouble();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}