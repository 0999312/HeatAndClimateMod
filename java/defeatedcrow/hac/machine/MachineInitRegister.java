package defeatedcrow.hac.machine;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.block.BlockBoilerTurbine;
import defeatedcrow.hac.machine.block.BlockCatapult;
import defeatedcrow.hac.machine.block.BlockConveyor;
import defeatedcrow.hac.machine.block.BlockCrank_S;
import defeatedcrow.hac.machine.block.BlockCreativeBox;
import defeatedcrow.hac.machine.block.BlockDynamo;
import defeatedcrow.hac.machine.block.BlockFan;
import defeatedcrow.hac.machine.block.BlockFauset;
import defeatedcrow.hac.machine.block.BlockGearBox;
import defeatedcrow.hac.machine.block.BlockGearBox_SUS;
import defeatedcrow.hac.machine.block.BlockHandCrank;
import defeatedcrow.hac.machine.block.BlockHeatExchanger;
import defeatedcrow.hac.machine.block.BlockHopperFilter;
import defeatedcrow.hac.machine.block.BlockHopperFluid;
import defeatedcrow.hac.machine.block.BlockIBC;
import defeatedcrow.hac.machine.block.BlockKineticMotor;
import defeatedcrow.hac.machine.block.BlockPressMachine;
import defeatedcrow.hac.machine.block.BlockReactor;
import defeatedcrow.hac.machine.block.BlockRedBox;
import defeatedcrow.hac.machine.block.BlockShaft_L;
import defeatedcrow.hac.machine.block.BlockShaft_L_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_S;
import defeatedcrow.hac.machine.block.BlockShaft_S_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TA;
import defeatedcrow.hac.machine.block.BlockShaft_TA_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TB;
import defeatedcrow.hac.machine.block.BlockShaft_TB_SUS;
import defeatedcrow.hac.machine.block.BlockStoneMill;
import defeatedcrow.hac.machine.block.BlockWaterPump;
import defeatedcrow.hac.machine.block.BlockWatermill;
import defeatedcrow.hac.machine.block.BlockWindmill;
import defeatedcrow.hac.machine.block.BlockWindmill_L;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.machine.block.ItemIBC;
import defeatedcrow.hac.machine.item.ItemAlloyMold;
import defeatedcrow.hac.machine.item.ItemAluminiumMold;
import defeatedcrow.hac.machine.item.ItemCatalyst;
import defeatedcrow.hac.machine.item.ItemMachineMaterial;
import defeatedcrow.hac.machine.item.ItemReagents;
import defeatedcrow.hac.machine.item.ItemSteelMold;
import defeatedcrow.hac.machine.item.ItemSynthetic;
import defeatedcrow.hac.machine.item.ItemTorqueChecker;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainMaterialRegister;
import defeatedcrow.hac.main.block.fluid.DCFluidBlockBase;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MachineInitRegister {

	private MachineInitRegister() {}

	public static void load() {
		loadBlocks();
		loadItems();
		loadFluids();

		if (ModuleConfig.machine) {
			loadCreativeTab();
		}
	}

	static void loadBlocks() {
		MachineInit.windmill = new BlockWindmill(ClimateCore.PACKAGE_BASE + "_device_windmill");
		registerTierBlock(MachineInit.windmill, ClimateCore.PACKAGE_BASE + "_device_windmill", 1);

		MachineInit.windmill_l = new BlockWindmill_L(ClimateCore.PACKAGE_BASE + "_device_windmill_l");
		registerTierBlock(MachineInit.windmill_l, ClimateCore.PACKAGE_BASE + "_device_windmill_l", 1);

		MachineInit.shaft_s = new BlockShaft_S(ClimateCore.PACKAGE_BASE + "_device_shaft_s");
		registerTierBlock(MachineInit.shaft_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s", 1);

		MachineInit.shaft_l = new BlockShaft_L(ClimateCore.PACKAGE_BASE + "_device_shaft_l");
		registerTierBlock(MachineInit.shaft_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l", 1);

		MachineInit.shaft_t_a = new BlockShaft_TA(ClimateCore.PACKAGE_BASE + "_device_shaft_ta");
		registerTierBlock(MachineInit.shaft_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta", 1);

		MachineInit.shaft_t_b = new BlockShaft_TB(ClimateCore.PACKAGE_BASE + "_device_shaft_tb");
		registerTierBlock(MachineInit.shaft_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb", 1);

		MachineInit.gearbox = new BlockGearBox(ClimateCore.PACKAGE_BASE + "_device_gearbox");
		registerTierBlock(MachineInit.gearbox, ClimateCore.PACKAGE_BASE + "_device_gearbox", 2);

		MachineInit.piston = new BlockCrank_S(ClimateCore.PACKAGE_BASE + "_device_crank_s");
		registerTierBlock(MachineInit.piston, ClimateCore.PACKAGE_BASE + "_device_crank_s", 1);

		MachineInit.handcrank = new BlockHandCrank(ClimateCore.PACKAGE_BASE + "_device_handcrank");
		registerTierBlock(MachineInit.handcrank, ClimateCore.PACKAGE_BASE + "_device_handcrank", 1);

		MachineInit.stonemill = new BlockStoneMill(ClimateCore.PACKAGE_BASE + "_device_stonemill");
		registerTierBlock(MachineInit.stonemill, ClimateCore.PACKAGE_BASE + "_device_stonemill", 2);

		MachineInit.fan = new BlockFan(ClimateCore.PACKAGE_BASE + "_device_fan");
		registerTierBlock(MachineInit.fan, ClimateCore.PACKAGE_BASE + "_device_fan", 1);

		MachineInit.watermill = new BlockWatermill(ClimateCore.PACKAGE_BASE + "_device_watermill");
		registerTierBlock(MachineInit.watermill, ClimateCore.PACKAGE_BASE + "_device_watermill", 2);

		MachineInit.redbox = new BlockRedBox(ClimateCore.PACKAGE_BASE + "_device_redbox");
		registerTierBlock(MachineInit.redbox, ClimateCore.PACKAGE_BASE + "_device_redbox", 2);

		MachineInit.conveyor = new BlockConveyor(ClimateCore.PACKAGE_BASE + "_device_conveyor");
		registerTierBlock(MachineInit.conveyor, ClimateCore.PACKAGE_BASE + "_device_conveyor", 2);

		MachineInit.catapult = new BlockCatapult(ClimateCore.PACKAGE_BASE + "_device_catapult");
		registerTierBlock(MachineInit.catapult, ClimateCore.PACKAGE_BASE + "_device_catapult", 2);

		MachineInit.hopperFilter = new BlockHopperFilter(ClimateCore.PACKAGE_BASE + "_device_hopper_filter");
		registerTierBlock(MachineInit.hopperFilter, ClimateCore.PACKAGE_BASE + "_device_hopper_filter", 2);
		ClimateMain.proxy.regTEJson(MachineInit.hopperFilter, "dcs_climate", "dcs_device_hopper_filter", "machine");

		MachineInit.fauset = new BlockFauset(ClimateCore.PACKAGE_BASE + "_device_fauset");
		registerTierBlock(MachineInit.fauset, ClimateCore.PACKAGE_BASE + "_device_fauset", 2);

		MachineInit.IBC = new BlockIBC(ClimateCore.PACKAGE_BASE + "_device_ibc");
		MachineInit.IBC.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_ibc");
		GameRegistry.register(MachineInit.IBC);
		GameRegistry.register(new ItemIBC(MachineInit.IBC));

		MachineInit.hopperFluid = new BlockHopperFluid(ClimateCore.PACKAGE_BASE + "_device_hopper_fluid");
		registerTierBlock(MachineInit.hopperFluid, ClimateCore.PACKAGE_BASE + "_device_hopper_fluid", 2);

		MachineInit.waterPump = new BlockWaterPump(ClimateCore.PACKAGE_BASE + "_device_water_pump");
		registerTierBlock(MachineInit.waterPump, ClimateCore.PACKAGE_BASE + "_device_water_pump", 2);

		MachineInit.heatPump = new BlockHeatExchanger(ClimateCore.PACKAGE_BASE + "_device_heat_exchanger");
		registerTierBlock(MachineInit.heatPump, ClimateCore.PACKAGE_BASE + "_device_heat_exchanger", 2);

		MachineInit.shaft2_s = new BlockShaft_S_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus");
		registerTierBlock(MachineInit.shaft2_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus", 3);

		MachineInit.shaft2_l = new BlockShaft_L_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus");
		registerTierBlock(MachineInit.shaft2_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus", 3);

		MachineInit.shaft2_t_a = new BlockShaft_TA_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus");
		registerTierBlock(MachineInit.shaft2_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus", 3);

		MachineInit.shaft2_t_b = new BlockShaft_TB_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus");
		registerTierBlock(MachineInit.shaft2_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus", 3);

		MachineInit.gearbox2 = new BlockGearBox_SUS(ClimateCore.PACKAGE_BASE + "_device_gearbox_sus");
		registerTierBlock(MachineInit.gearbox2, ClimateCore.PACKAGE_BASE + "_device_gearbox_sus", 3);

		MachineInit.boilerTurbine = new BlockBoilerTurbine(ClimateCore.PACKAGE_BASE + "_device_boiler_turbine");
		registerTierBlock(MachineInit.boilerTurbine, ClimateCore.PACKAGE_BASE + "_device_boiler_turbine", 3);

		MachineInit.motor = new BlockKineticMotor(ClimateCore.PACKAGE_BASE + "_device_kinetic_motor");
		registerTierBlock(MachineInit.motor, ClimateCore.PACKAGE_BASE + "_device_kinetic_motor", 3);

		MachineInit.dynamo = new BlockDynamo(ClimateCore.PACKAGE_BASE + "_device_dynamo");
		registerTierBlock(MachineInit.dynamo, ClimateCore.PACKAGE_BASE + "_device_dynamo", 3);

		MachineInit.reactor = new BlockReactor(ClimateCore.PACKAGE_BASE + "_device_reactor");
		registerTierBlock(MachineInit.reactor, ClimateCore.PACKAGE_BASE + "_device_reactor", 3);

		MachineInit.pressMachine = new BlockPressMachine(ClimateCore.PACKAGE_BASE + "_device_press_machine");
		registerTierBlock(MachineInit.pressMachine, ClimateCore.PACKAGE_BASE + "_device_press_machine", 3);

		MachineInit.creativeBox = new BlockCreativeBox(ClimateCore.PACKAGE_BASE + "_device_creative_box");
		registerTierBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_BASE + "_device_creative_box", 3);

	}

	static void loadItems() {
		MachineInit.machimeMaterials = new ItemMachineMaterial(1)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mechanical");
		GameRegistry.register(MachineInit.machimeMaterials.setRegistryName(ClimateCore.PACKAGE_BASE + "_mechanical"));

		MachineInit.mold = new ItemSteelMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mold");
		GameRegistry.register(MachineInit.mold.setRegistryName(ClimateCore.PACKAGE_BASE + "_mold"));

		MachineInit.moldAluminium = new ItemAluminiumMold()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_aluminium_mold");
		GameRegistry.register(MachineInit.moldAluminium.setRegistryName(ClimateCore.PACKAGE_BASE + "_aluminium_mold"));

		MachineInit.moldAlloy = new ItemAlloyMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_alloy_mold");
		GameRegistry.register(MachineInit.moldAlloy.setRegistryName(ClimateCore.PACKAGE_BASE + "_alloy_mold"));

		MachineInit.torqueChecker = new ItemTorqueChecker()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_torque_checker");
		GameRegistry.register(MachineInit.torqueChecker.setRegistryName(ClimateCore.PACKAGE_BASE + "_torque_checker"));

		MachineInit.reagent = new ItemReagents().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_reagent");
		GameRegistry.register(MachineInit.reagent.setRegistryName(ClimateCore.PACKAGE_BASE + "_reagent"));

		MachineInit.synthetic = new ItemSynthetic().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_synthetic");
		GameRegistry.register(MachineInit.synthetic.setRegistryName(ClimateCore.PACKAGE_BASE + "_synthetic"));

		MachineInit.catalyst = new ItemCatalyst().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_catalyst");
		GameRegistry.register(MachineInit.catalyst.setRegistryName(ClimateCore.PACKAGE_BASE + "_catalyst"));
	}

	static void loadCreativeTab() {
		MachineInit.windmill.setCreativeTab(ClimateMain.machine);
		MachineInit.windmill_l.setCreativeTab(ClimateMain.machine);
		MachineInit.handcrank.setCreativeTab(ClimateMain.machine);

		MachineInit.shaft_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox.setCreativeTab(ClimateMain.machine);

		MachineInit.piston.setCreativeTab(ClimateMain.machine);

		MachineInit.conveyor.setCreativeTab(ClimateMain.machine);
		MachineInit.stonemill.setCreativeTab(ClimateMain.machine);
		MachineInit.fan.setCreativeTab(ClimateMain.machine);
		MachineInit.redbox.setCreativeTab(ClimateMain.machine);
		MachineInit.catapult.setCreativeTab(ClimateMain.machine);
		MachineInit.hopperFilter.setCreativeTab(ClimateMain.machine);

		MachineInit.fauset.setCreativeTab(ClimateMain.machine);
		MachineInit.IBC.setCreativeTab(ClimateMain.machine);
		MachineInit.hopperFluid.setCreativeTab(ClimateMain.machine);
		MachineInit.heatPump.setCreativeTab(ClimateMain.machine);
		MachineInit.waterPump.setCreativeTab(ClimateMain.machine);
		MachineInit.watermill.setCreativeTab(ClimateMain.machine);

		MachineInit.shaft2_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox2.setCreativeTab(ClimateMain.machine);

		MachineInit.boilerTurbine.setCreativeTab(ClimateMain.machine);
		MachineInit.motor.setCreativeTab(ClimateMain.machine);
		MachineInit.dynamo.setCreativeTab(ClimateMain.machine);
		MachineInit.creativeBox.setCreativeTab(ClimateMain.machine);

		MachineInit.pressMachine.setCreativeTab(ClimateMain.machine);

		MachineInit.torqueChecker.setCreativeTab(ClimateMain.machine);
		MachineInit.machimeMaterials.setCreativeTab(ClimateCore.climate);
		MachineInit.mold.setCreativeTab(ClimateMain.machine);

		if (ModuleConfig.machine_advanced) {
			MachineInit.reactor.setCreativeTab(ClimateMain.machine);

			MachineInit.moldAluminium.setCreativeTab(ClimateMain.machine);
			MachineInit.moldAlloy.setCreativeTab(ClimateMain.machine);

			MachineInit.reagent.setCreativeTab(ClimateMain.machine);
			MachineInit.synthetic.setCreativeTab(ClimateMain.machine);
			MachineInit.catalyst.setCreativeTab(ClimateMain.machine);
		}
	}

	static void loadFluids() {

		MachineInit.hydrogen = new Fluid("dcs.hydrogen",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hydrogen_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hydrogen_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".hydrogen").setDensity(-1000).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.hydrogen);
		MachineInit.hydrogenBlock = new DCFluidBlockBase(MachineInit.hydrogen, "hydrogen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen");
		MainMaterialRegister.registerBlock(MachineInit.hydrogenBlock,
				ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen");
		MachineInit.hydrogen.setBlock(MachineInit.hydrogenBlock);

		MachineInit.ammonia = new Fluid("dcs.ammonia",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/ammonia_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/ammonia_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".ammonia").setDensity(-1000).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.ammonia);
		MachineInit.ammoniaBlock = new DCFluidBlockBase(MachineInit.ammonia, "ammonia_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia");
		MainMaterialRegister.registerBlock(MachineInit.ammoniaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia");
		MachineInit.ammonia.setBlock(MachineInit.ammoniaBlock);

		MachineInit.nitricAcid = new Fluid("dcs.nitric_acid",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitric_acid_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitric_acid_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".nitric_acid").setDensity(1200)
						.setViscosity(1200);
		FluidRegistry.registerFluid(MachineInit.nitricAcid);
		MachineInit.nitricAcidBlock = new DCFluidBlockBase(MachineInit.nitricAcid, "nitric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid");
		MainMaterialRegister.registerBlock(MachineInit.nitricAcidBlock,
				ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid");
		MachineInit.nitricAcid.setBlock(MachineInit.nitricAcidBlock);

		MachineInit.sulfuricAcid = new Fluid("dcs.sulfuric_acid",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/sulfuric_acid_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/sulfuric_acid_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".sulfuric_acid").setDensity(1200)
						.setViscosity(1200);
		FluidRegistry.registerFluid(MachineInit.sulfuricAcid);
		MachineInit.sulfuricAcidBlock = new DCFluidBlockBase(MachineInit.sulfuricAcid, "sulfuric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid");
		MainMaterialRegister.registerBlock(MachineInit.sulfuricAcidBlock,
				ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid");
		MachineInit.sulfuricAcid.setBlock(MachineInit.sulfuricAcidBlock);

		MachineInit.fuelGas = new Fluid("dcs.fuel_gas",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_gas_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_gas_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_gas").setDensity(-500).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.fuelGas);
		MachineInit.fuelGasBlock = new DCFluidBlockBase(MachineInit.fuelGas, "fuel_gas_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas");
		MainMaterialRegister.registerBlock(MachineInit.fuelGasBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas");
		MachineInit.fuelGas.setBlock(MachineInit.fuelGasBlock);

		MachineInit.fuelOil = new Fluid("dcs.fuel_oil",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_oil_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_oil_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(MachineInit.fuelOil);
		MachineInit.fuelOilBlock = new DCFluidBlockBase(MachineInit.fuelOil, "fuel_oil_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil");
		MainMaterialRegister.registerBlock(MachineInit.fuelOilBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil");
		MachineInit.fuelOil.setBlock(MachineInit.fuelOilBlock);

		// bucket
		FluidRegistry.addBucketForFluid(MachineInit.hydrogen);
		FluidRegistry.addBucketForFluid(MachineInit.ammonia);
		FluidRegistry.addBucketForFluid(MachineInit.nitricAcid);
		FluidRegistry.addBucketForFluid(MachineInit.sulfuricAcid);
		FluidRegistry.addBucketForFluid(MachineInit.fuelGas);
		FluidRegistry.addBucketForFluid(MachineInit.fuelOil);
	}

	public static void registerTierBlock(Block block, String name, int i) {
		Block reg = block.setRegistryName(name);
		GameRegistry.register(reg);
		GameRegistry.register(new ItemBlockHighTier(reg, i));
	}

}