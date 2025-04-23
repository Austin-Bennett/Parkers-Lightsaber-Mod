package austin.lightsabers.datagen;

import austin.lightsabers.item.LightsaberItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //example
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //example
        //itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        registerLightsaber(itemModelGenerator, LightsaberItems.RED_LIGHTSABER, "red_lightsaber");
        registerLightsaber(itemModelGenerator, LightsaberItems.BLUE_LIGHTSABER, "blue_lightsaber");
        registerLightsaber(itemModelGenerator, LightsaberItems.GREEN_LIGHTSABER, "green_lightsaber");
        registerLightsaber(itemModelGenerator, LightsaberItems.PURPLE_LIGHTSABER, "purple_lightsaber");
        registerLightsaber(itemModelGenerator, LightsaberItems.YELLOW_LIGHTSABER, "yellow_lightsaber");

        itemModelGenerator.register(LightsaberItems.KYBER_SHARD, Models.GENERATED);

        itemModelGenerator.register(LightsaberItems.RAW_KYBER_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(LightsaberItems.RED_KYBER_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(LightsaberItems.BLUE_KYBER_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(LightsaberItems.GREEN_KYBER_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(LightsaberItems.PURPLE_KYBER_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(LightsaberItems.YELLOW_KYBER_CRYSTAL, Models.GENERATED);
    }

    void registerLightsaber(ItemModelGenerator itemModelGenerator, Item lightsaber, String texture_name) {
        itemModelGenerator.register(lightsaber,
                new Model(
                        Optional.of(new Identifier("lightsabers", "item/lightsaber_base")),
                        Optional.empty(),
                        TextureKey.of("lightsabers:item/" + texture_name, TextureKey.LAYER0)
                )
        );
    }
}
