package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityProjSilver extends EntityProjBase {

	public EntityProjSilver(World worldIn) {
		super(worldIn);
	}

	public EntityProjSilver(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSilver(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerSilver, 1, 0);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = 6.0F + this.world.rand.nextFloat();
		if (target != null && target instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) target;
			if (liv.isEntityUndead()) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return this.shootingEntity == null ? DamageSource.causeArrowDamage(this, this)
				: DamageSource.causeArrowDamage(this, this.shootingEntity);
	}

	@Override
	public boolean isInWater() {
		return false;
	}

}
