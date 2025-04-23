package austin.lightsabers.item;

import austin.lightsabers.LightsabersMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static austin.lightsabers.item.LightsaberItems.*;

public class LightsaberItemGroup {

    public static final ItemGroup LightsaberGroup =
            Registry.register(Registries.ITEM_GROUP, new Identifier(LightsabersMod.MOD_ID, "lightsaber_igroup"),
                    FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lightsabers")).icon(() -> new ItemStack(RED_LIGHTSABER)
                    ).entries((displayContext, entries) -> {
                        entries.add(KYBER_SHARD);
                        entries.add(RAW_KYBER_CRYSTAL);
                        entries.add(RED_KYBER_CRYSTAL);
                        entries.add(BLUE_KYBER_CRYSTAL);
                        entries.add(GREEN_KYBER_CRYSTAL);
                        entries.add(PURPLE_KYBER_CRYSTAL);
                        entries.add(YELLOW_KYBER_CRYSTAL);
                        entries.add(RED_LIGHTSABER);
                        entries.add(BLUE_LIGHTSABER);
                        entries.add(GREEN_LIGHTSABER);
                        entries.add(PURPLE_LIGHTSABER);
                        entries.add(YELLOW_LIGHTSABER);
                    }).build());

    public static void register() {
        LightsabersMod.LOGGER.info("Registering ItemGroup" + LightsabersMod.sign());
    }
}
