package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;

public class RoundBreadEntity extends FoodEntityBase {

	public RoundBreadEntity(World worldIn) {
		super(worldIn);
	}

	public RoundBreadEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public RoundBreadEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	public boolean canCookingClimate(IClimate climate) {
		if (climate != null) {
			boolean a = climate.getHeat() == DCHeatTier.OVEN || climate.getHeat() == DCHeatTier.KILN;
			boolean b = climate.getHumidity() != DCHumidity.UNDERWATER;
			return a && b;
		}
		return false;
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.bread, 1, 0),
				new ItemStack(FoodInit.bread, 1, 1) };
	}
}