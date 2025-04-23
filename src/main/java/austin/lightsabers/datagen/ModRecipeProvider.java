package austin.lightsabers.datagen;

import austin.lightsabers.LightsabersMod;
import austin.lightsabers.item.Lightsaber;
import austin.lightsabers.item.LightsaberItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    //private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY...);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        //Examples
        //offerSmelting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.7f, 200, "ruby");
        //offerReversibleCompactingRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_RUBY, 1)
//                .pattern("SSS")
//                .pattern("SCS")
//                .pattern("SSS")
//                .input('S', Items.STONE)
//                .input('C', ModItems.RUBY)
//                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
//                .criterion(hasItem(ModItems.Ruby), conditionsFromItem(ModItems.RUBY))
//                .offerTo(consumer, new Identifier(getRecipeName(ModItems.RAW_RUBY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, LightsaberItems.KYBER_SHARD)
                .pattern("DDD")
                .pattern("DAD")
                .pattern("DED")
                .input('D', Items.DIAMOND)
                .input('A', Items.AMETHYST_SHARD)
                .input('E', Items.ENDER_PEARL)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(consumer, "kyber_shard_normal");

        offerReversibleCompactingRecipes(consumer, RecipeCategory.MISC, LightsaberItems.KYBER_SHARD, RecipeCategory.MISC, LightsaberItems.RAW_KYBER_CRYSTAL);

        offerSmithingRecipe(consumer, Items.AIR, LightsaberItems.RAW_KYBER_CRYSTAL, Items.NETHERITE_INGOT, LightsaberItems.RED_KYBER_CRYSTAL,
                RecipeCategory.MISC);

        offerSmithingRecipe(consumer, Items.AIR, LightsaberItems.RAW_KYBER_CRYSTAL, Items.DIAMOND, LightsaberItems.BLUE_KYBER_CRYSTAL,
                RecipeCategory.MISC);

        offerSmithingRecipe(consumer, Items.AIR, LightsaberItems.RAW_KYBER_CRYSTAL, Items.EMERALD, LightsaberItems.GREEN_KYBER_CRYSTAL,
                RecipeCategory.MISC);

        offerSmithingRecipe(consumer, Items.AIR, LightsaberItems.RAW_KYBER_CRYSTAL, Items.ENDER_EYE, LightsaberItems.PURPLE_KYBER_CRYSTAL,
                RecipeCategory.MISC);

        offerSmithingRecipe(consumer, Items.AIR, LightsaberItems.RAW_KYBER_CRYSTAL, Items.GOLD_INGOT, LightsaberItems.YELLOW_KYBER_CRYSTAL,
                RecipeCategory.MISC);

        offerLightsaberRecipe(consumer, LightsaberItems.RED_KYBER_CRYSTAL, LightsaberItems.RED_LIGHTSABER);
        offerLightsaberRecipe(consumer, LightsaberItems.GREEN_KYBER_CRYSTAL, LightsaberItems.GREEN_LIGHTSABER);
        offerLightsaberRecipe(consumer, LightsaberItems.BLUE_KYBER_CRYSTAL, LightsaberItems.BLUE_LIGHTSABER);
        offerLightsaberRecipe(consumer, LightsaberItems.PURPLE_KYBER_CRYSTAL, LightsaberItems.PURPLE_LIGHTSABER);
        offerLightsaberRecipe(consumer, LightsaberItems.YELLOW_KYBER_CRYSTAL, LightsaberItems.YELLOW_LIGHTSABER);
    }

    void offerLightsaberRecipe(Consumer<RecipeJsonProvider> consumer, ItemConvertible KyberCrystal, Lightsaber Result) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Result)
                .pattern(" KR")
                .pattern("IBI")
                .pattern(" I ")
                .input('K', KyberCrystal)
                .input('R', Items.REDSTONE)
                .input('I', Items.IRON_INGOT)
                .input('B', Items.BEACON)
                .criterion(hasItem(KyberCrystal), conditionsFromItem(KyberCrystal))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.BEACON), conditionsFromItem(Items.BEACON))
                .offerTo(consumer, getRecipeName(Result));
    }

    void offerSmithingRecipe(Consumer<RecipeJsonProvider> consumer, ItemConvertible template, ItemConvertible base,
                             ItemConvertible Addition, ItemConvertible Result, RecipeCategory category) {
        var x = SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template.asItem()),
                        Ingredient.ofItems(base.asItem()),
                        Ingredient.ofItems(Addition.asItem()),
                        category, Result.asItem())
                .criterion(hasItem(base.asItem()), conditionsFromItem(base.asItem()))
                .criterion(hasItem(Addition.asItem()), conditionsFromItem(Addition.asItem()));
        if ((template.asItem() != Items.AIR)) {
            x.criterion(hasItem(template.asItem()), conditionsFromItem(template.asItem()));
        }
        x.offerTo(consumer, getRecipeName(Result.asItem()));
    }

    void offerSmithingRecipe(Consumer<RecipeJsonProvider> consumer, @Nullable ItemConvertible template, ItemConvertible base,
                             ItemConvertible Addition, ItemConvertible Result, RecipeCategory category, String id) {
        var x = SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template.asItem()),
                        Ingredient.ofItems(base.asItem()),
                        Ingredient.ofItems(Addition.asItem()),
                        category, Result.asItem())
                .criterion(hasItem(base.asItem()), conditionsFromItem(base.asItem()))
                .criterion(hasItem(Addition.asItem()), conditionsFromItem(Addition.asItem()));

        if ((template.asItem() != Items.AIR)) {
            x.criterion(hasItem(template.asItem()), conditionsFromItem(template.asItem()));
        }
        x.offerTo(consumer, id);
    }
}
