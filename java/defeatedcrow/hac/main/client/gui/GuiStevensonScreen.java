package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStevensonScreen extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/stevenson_screen_gui.png");
	private final EntityPlayer player;
	private final TileStevensonScreen tile;

	private IClimate climate;
	private int weather = 0;

	public GuiStevensonScreen(TileStevensonScreen t, EntityPlayer p) {
		super(new ContainerStevensonScreen(t, p));
		tile = t;
		player = p;
		climate = t.getClimate();
		if (t.getWorld() != null) {
			World world = t.getWorld();
			Biome biome = world.getBiomeForCoordsBody(t.getPos());
			boolean snow = biome.isSnowyBiome()
					|| (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN) && t.getPos().getY() > 100);
			boolean dry = !biome.canRain();
			if (world.getRainStrength(1.0F) > 0.2F && !dry) {
				if (world.getThunderStrength(1.0F) > 0.5F) {
					weather = snow ? 4 : 3;
				} else {
					weather = snow ? 2 : 1;
				}
			}

		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int adjX = this.xSize / 2;
		String s = I18n.translateToLocal("dcs.gui.device.stevenson_screen");
		this.fontRendererObj.drawString(s, adjX - this.fontRendererObj.getStringWidth(s) / 2, 10, 4210752);

		int date = DCTimeHelper.getDay(tile.getWorld());
		int time = DCTimeHelper.currentTime(tile.getWorld());
		boolean day = DCTimeHelper.isDayTime(tile.getWorld());
		boolean pm = false;
		if (time > 12) {
			time -= 12;
			pm = true;
		}
		int color = day ? 0x005050 : 0x500050;

		String s1 = date + " DAY";
		this.fontRendererObj.drawString(s1, adjX - 15 - this.fontRendererObj.getStringWidth(s1) / 2, 22, 4210752);

		String s2 = time + (pm ? " PM" : " AM");
		this.fontRendererObj.drawString(s2, adjX + 15 - this.fontRendererObj.getStringWidth(s1) / 2, 22, color);

		Weather current = Weather.getWeather(weather, day);
		String s3 = current.name;
		this.fontRendererObj.drawString(s3, adjX - this.fontRendererObj.getStringWidth(s3) / 2, 60, 4210752);

		String s4 = "TEMPERATURE";
		this.fontRendererObj.drawString(s4, adjX - 25 - this.fontRendererObj.getStringWidth(s4) / 2, 80, 4210752);

		String s5 = "HUMIDITY";
		this.fontRendererObj.drawString(s5, adjX - 25 - this.fontRendererObj.getStringWidth(s5) / 2, 97, 4210752);

		String s6 = "AIRFLOW";
		this.fontRendererObj.drawString(s6, adjX - 25 - this.fontRendererObj.getStringWidth(s6) / 2, 114, 4210752);

		if (climate != null) {
			DCHeatTier temp = climate.getHeat();
			DCHumidity hum = climate.getHumidity();
			DCAirflow air = climate.getAirflow();

			String s7 = temp.name();
			this.fontRendererObj.drawString(s7, adjX + 20 - this.fontRendererObj.getStringWidth(s7) / 2, 76, 4210752);

			String s8 = hum.name();
			this.fontRendererObj.drawString(s8, adjX + 20 - this.fontRendererObj.getStringWidth(s8) / 2, 93, 4210752);

			String s9 = air.name();
			this.fontRendererObj.drawString(s9, adjX + 20 - this.fontRendererObj.getStringWidth(s9) / 2, 110, 4210752);
		}

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		boolean day = DCTimeHelper.isDayTime(tile.getWorld());
		int x1 = day ? 176 : 200;
		int y1 = weather * 18;
		this.drawTexturedModalRect(i + 76, j + 40, x1, y1, 24, 18);

		if (climate != null) {
			DCHeatTier temp = climate.getHeat();
			DCHumidity hum = climate.getHumidity();
			DCAirflow air = climate.getAirflow();

			int x2 = 20 * temp.getID();
			this.drawTexturedModalRect(i + 89, j + 85, x2, 167, 20, 3);
			this.drawTexturedModalRect(i + 109, j + 85, x2, 167, 20, 3);

			int x3 = 40 * hum.getID();
			this.drawTexturedModalRect(i + 89, j + 102, x3, 171, 40, 3);

			int x4 = 40 * air.getID();
			this.drawTexturedModalRect(i + 89, j + 119, x4, 175, 40, 3);
		}

	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

	public enum Weather {

		SUNNY(0, true, "Sunny Day"),
		RAIN(1, true, "Rainfall"),
		SNOW(2, true, "Snowfall"),
		THUNDER(3, true, "Thunderstorm"),
		THUNDERSNOW(4, true, "Thundersnow"),
		LUNA(0, false, "Starry Sky"),
		L_RAIN(1, false, "Rainy Night"),
		L_SNOW(2, false, "Snowy Night"),
		L_THUNDER(3, false, "Thunderstorm Night"),
		L_THUNDERSNOW(4, false, "Thundersnow Night");

		private final int id;
		private final boolean day;
		private final String name;

		private Weather(int i, boolean d, String n) {
			id = i;
			day = d;
			name = n;
		}

		public static Weather getWeather(int i, boolean d) {
			switch (i) {
			case 0:
				return d ? SUNNY : LUNA;
			case 1:
				return d ? RAIN : L_RAIN;
			case 2:
				return d ? SNOW : L_SNOW;
			case 3:
				return d ? THUNDER : L_THUNDER;
			case 4:
				return d ? THUNDERSNOW : L_THUNDERSNOW;
			default:
				return d ? SUNNY : LUNA;
			}
		}
	}

}
