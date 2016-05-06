package defeatedcrow.hac.main.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.main.MainInit;

public class MachineRecipeRegister {

	public static void load() {
		loadOreRecipes();
		loadContRecipes();
		loadBuildRecipes();
		loadVanillaRecipes();
		loadSmelting();
	}

	static void loadOreRecipes() {
		// 金属精錬。ごく一部はKILNでも焼けるが、ほとんどがSMELTを要求する。
		// copper
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 0));
		// zinc
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 1));
		// nickel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 2), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 2));
		// silver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 3), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 3));
		// brass
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 4), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 4));
		// steel
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 5), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 5));
		// iron
		ClimateSmelting iron = new ClimateSmelting(new ItemStack(Blocks.iron_block, 1, 0), null, DCHeatTier.KILN, null, DCAirflow.TIGHT,
				0F, false, new ItemStack(MainInit.dustBlock, 1, 5));
		iron.requiredHeat().remove(DCHeatTier.SMELTING);
		RecipeAPI.registerSmelting.addRecipe(iron, DCHeatTier.KILN);
		// nickelsilver
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 6), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 6));
		// magnet
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.metalBlock, 1, 7), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.dustBlock, 1, 7));

		// dust個別精錬
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 0));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 1), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 1));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 2), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 2));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 3), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 3));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.gold_ingot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 4));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(Items.iron_ingot, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 5));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 5), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 6));
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.oreIngot, 1, 7), DCHeatTier.SMELTING, null, DCAirflow.TIGHT, false,
				new ItemStack(MainInit.oreDust, 1, 7));

		// 3x3
		RecipeAPI.registerRecipes.addRecipe(new ItemStack(MainInit.metalBlock, 1, 0), DCHeatTier.KILN, null, DCAirflow.TIGHT, false,
				new Object[] {
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper",
						"dustCopper" });

	}

	static void loadContRecipes() {
		// コンテナ類の自然変化
		// log
		for (int i = 0; i < 6; i++) {
			ClimateSmelting recipe = new ClimateSmelting(new ItemStack(MainInit.logCont, 1, 6), null, DCHeatTier.KILN, null,
					DCAirflow.TIGHT, 0, false, new ItemStack(MainInit.logCont, 1, i));
			RecipeAPI.registerSmelting.addRecipe(recipe, DCHeatTier.KILN);
		}

		// burnt crops
		ClimateSmelting apple = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 9), null, DCHeatTier.OVEN, null, DCAirflow.TIGHT,
				0, false, new ItemStack(MainInit.cropCont, 1, 0));
		apple.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(apple, DCHeatTier.OVEN);

		ClimateSmelting potato = new ClimateSmelting(new ItemStack(MainInit.cropCont, 1, 10), null, DCHeatTier.OVEN, null, DCAirflow.TIGHT,
				0, false, new ItemStack(MainInit.cropCont, 1, 1));
		potato.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(potato, DCHeatTier.OVEN);

		// 焼くと骨に、煮ると皮になる
		ClimateSmelting flesh1 = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 1), null, DCHeatTier.KILN, DCHumidity.DRY, null,
				0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh1.requiredHum().add(DCHumidity.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(flesh1, DCHeatTier.OVEN);

		ClimateSmelting flesh2 = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 2), null, DCHeatTier.KILN, DCHumidity.WET, null,
				0, false, new ItemStack(MainInit.dropCont, 1, 0));
		flesh2.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(flesh2, DCHeatTier.OVEN);

		// AMTにもあった、火薬と粘土の互換
		ClimateSmelting powder = new ClimateSmelting(new ItemStack(MainInit.miscCont, 1, 0), null, DCHeatTier.HOT, DCHumidity.WET, null, 0,
				false, new ItemStack(MainInit.dropCont, 1, 4));
		powder.requiredHum().add(DCHumidity.UNDERWATER);
		RecipeAPI.registerSmelting.addRecipe(powder, DCHeatTier.HOT);

		ClimateSmelting clay = new ClimateSmelting(new ItemStack(MainInit.dropCont, 1, 4), null, DCHeatTier.HOT, DCHumidity.DRY,
				DCAirflow.NORMAL, 0, false, new ItemStack(MainInit.miscCont, 1, 0));
		clay.requiredAir().add(DCAirflow.FLOW);
		clay.requiredAir().add(DCAirflow.WIND);
		RecipeAPI.registerSmelting.addRecipe(clay, DCHeatTier.HOT);

	}

	static void loadBuildRecipes() {
		ClimateSmelting apple = new ClimateSmelting(new ItemStack(MainInit.selenite, 1, 0), null, DCHeatTier.KILN, null, DCAirflow.TIGHT,
				0, false, new ItemStack(MainInit.gemBlock, 1, 3));
		apple.requiredAir().add(DCAirflow.NORMAL);
		RecipeAPI.registerSmelting.addRecipe(apple, DCHeatTier.KILN);
	}

	static void loadSmelting() {
		// KILNはバニラカマドに対応する
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 0), new ItemStack(MainInit.metalBlock, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 1), new ItemStack(MainInit.metalBlock, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 3), new ItemStack(MainInit.metalBlock, 1, 3), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 4), new ItemStack(MainInit.metalBlock, 1, 4), 0.3F);
		GameRegistry.addSmelting(new ItemStack(MainInit.dustBlock, 1, 5), new ItemStack(Blocks.iron_block, 1, 0), 0.3F);

		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 0), new ItemStack(MainInit.oreIngot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 1), new ItemStack(MainInit.oreIngot, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 3), new ItemStack(MainInit.oreIngot, 1, 3), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 4), new ItemStack(Items.gold_ingot, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MainInit.oreDust, 1, 5), new ItemStack(Items.iron_ingot, 1, 0), 0.1F);

		GameRegistry.addSmelting(new ItemStack(MainInit.gemBlock, 1, 3), new ItemStack(MainInit.selenite, 1, 0), 0.15F);

		// gems
		GameRegistry.addSmelting(new ItemStack(Items.flint, 1, 0), new ItemStack(MainInit.gems, 1, 1), 0.2F);
	}

	static void loadVanillaRecipes() {

	}

}