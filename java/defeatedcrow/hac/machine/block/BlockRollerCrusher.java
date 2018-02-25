package defeatedcrow.hac.machine.block;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRollerCrusher extends BlockTorqueBase {

	public BlockRollerCrusher(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack heldItem = player.getHeldItem(hand);

			if (tile instanceof TileTorqueProcessor) {
				if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {
					((TileTorqueProcessor) tile).rotateFace();
				} else if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
			}
		}
		return true;
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			if (placer.rotationPitch < -75.0F) {
				face = EnumFacing.UP;
			} else if (placer.rotationPitch > 75.0F) {
				face = EnumFacing.DOWN;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite()));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		}
		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRollerCrusher();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof ITagGetter) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null) {
				((ITagGetter) tile).setNBT(tag);
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		int i = this.damageDropped(state);
		ItemStack drop = new ItemStack(this, 1, i);

		if (tile != null && tile instanceof ITagGetter) {
			NBTTagCompound tag = new NBTTagCompound();
			tag = ((ITagGetter) tile).getNBT(tag);
			if (tag != null) {
				drop.setTagCompound(tag);
			}
		}

		if (!world.isRemote) {
			EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
					drop);
			float f3 = 0.05F;
			entityitem.motionX = (float) world.rand.nextGaussian() * f3;
			entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.25F;
			entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
			world.spawnEntityInWorld(entityitem);
		}
		world.updateComparatorOutputLevel(pos, state.getBlock());
		super.breakBlock(world, pos, state);

	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

}
