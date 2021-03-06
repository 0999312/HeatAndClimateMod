package defeatedcrow.hac.machine.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityMagneticHover extends EntityScooter {

	public EntityMagneticHover(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.25F;
	}

	public EntityMagneticHover(World worldIn, double x, double y, double z) {
		super(worldIn);
	}

	public EntityMagneticHover(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ);
	}

	@Override
	public double getMountedYOffset() {
		return 0.4D;
	}

	@Override
	protected void addParticle() {
		if (this.getPowered()) {
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
				double px = posX - Math.sin(-rotationYaw * 0.017453292F) * 1D + world.rand.nextDouble() * 0.5D;
				double pz = posZ - Math.cos(rotationYaw * 0.017453292F) * 1D + world.rand.nextDouble() * 0.5D;
				Particle p = new ParticleBlink.Factory().createParticle(0, world, px, posY +
						0.25D, pz, 0.0D, 0.0D, 0.0D, new int[0]);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
			}
		}
	}

	@Override
	public void updateFalling(EntityPlayer rider) {
		// falling
		this.fallDistance = 0.0F;
		if (rider != null) {
			rider.fallDistance = 0.0F;
		}
		if (this.onGround || this.status == Status.ON_TILE || this.status == Status.IN_TILE) {
			if (this.motionY < 0F) {
				this.motionY *= 0.5D;
			}
			this.motionY += 0.02D;
		} else if (status == Status.UNDER_WATER) {
			if (this.motionY < 0F) {
				this.motionY *= 0.5D;
			}
			this.motionY += 0.02D;
		} else {
			if (this.motionY > 0F) {
				this.motionY *= 0.75D;
			}
			this.motionY -= 0.01D;
		}

		if (motionY < -0.05D) {
			this.motionY -= 0.05D;
		}
	}

	@Override
	protected void updateSpeed(EntityPlayer rider, boolean brake) {
		float yaw = this.rotationYaw * 0.017453292F;

		if (rider != null && !brake && getPowered() && rider.moveForward > 0F) {
			double d1 = -Math.sin(yaw);
			double d2 = Math.cos(yaw);
			double d9 = this.motionX * this.motionX + this.motionZ * this.motionZ;
			double d10 = Math.sqrt(d9);

			this.motionX += d1 * 0.2D;
			this.motionZ += d2 * 0.2D;

			if (d10 > getMaxSpeed()) {
				this.motionX = d1 * getMaxSpeed();
				this.motionZ = d2 * getMaxSpeed();
			}

			// DCLogger.debugLog("foward " + rider.moveForward + ", sp " + d9);
			rider.moveForward = 0F;
		} else {
			this.motionX *= 0.75D;
			this.motionZ *= 0.75D;
		}

	}

	@Override
	public int getBurnTime(Fluid fluid) {
		String s = fluid.getName();
		if (FluidDictionaryDC.matchFluid(fluid, MainInit.nitrogen))
			return 120;
		else if (fluid.getTemperature() < 100)
			return 60;
		return 0;
	}

	@Override
	protected float getMaxSpeed() {
		return 0.8F;
	}

	@Override
	protected Status getUnderGround() {
		AxisAlignedBB aabb = this.getEntityBoundingBox().grow(1.0D);
		int i = MathHelper.floor(aabb.minX);
		int j = MathHelper.ceil(aabb.maxX);
		int k = MathHelper.floor(aabb.minY);
		int l = MathHelper.ceil(aabb.minY);
		int i1 = MathHelper.floor(aabb.minZ);
		int j1 = MathHelper.ceil(aabb.maxZ);
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;
		BlockPos.PooledMutableBlockPos mpos = BlockPos.PooledMutableBlockPos.retain();

		try {
			for (int k1 = i; k1 < j; ++k1) {
				for (int l1 = k; l1 < l; ++l1) {
					for (int i2 = i1; i2 < j1; ++i2) {
						mpos.setPos(k1, l1, i2);
						IBlockState state = this.world.getBlockState(mpos);

						if (state.getMaterial().isLiquid()) {
							double d = state.getBoundingBox(world, mpos).maxY + mpos.getY();
							if (posY < d + 1D) {
								flag2 = true;
							}
						} else if (state.getCollisionBoundingBox(world, mpos) != null) {
							double d = state.getCollisionBoundingBox(world, mpos).maxY + mpos.getY();
							if (posY < d + 1D) {
								flag = true;
								if (posY < d) {
									flag3 = true;
								}
							}
						}
					}
				}
			}
		} finally {
			mpos.release();
		}

		return flag ? flag3 ? Status.IN_TILE : Status.ON_TILE : flag2 ? Status.UNDER_WATER : Status.IN_AIR;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.world.isRemote && !this.isDead && !this.isBeingRidden()) {
			if (this.isEntityInvulnerable(source)) {
				return false;
			} else if (source instanceof EntityDamageSource && !source.isProjectile() &&
					((EntityDamageSource) source).getTrueSource() instanceof EntityPlayer) {
				ItemStack itemstack = new ItemStack(MachineInit.magneticHover, 1, 0);

				NBTTagCompound tag = new NBTTagCompound();
				this.getNBTFromEntity(tag);
				itemstack.setTagCompound(tag);

				this.entityDropItem(itemstack, 0.0F);

				this.setDead();

			}
		}
		return false;
	}

}
