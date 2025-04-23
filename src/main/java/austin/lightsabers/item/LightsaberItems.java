package austin.lightsabers.item;

import austin.lightsabers.LightsabersMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class LightsaberItems {


    public static List<Lightsaber> lightsabers = new ArrayList<>();

    public static final Item KYBER_SHARD =
            registerItem("kyber_shard", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.COMMON).fireproof()));

    public static final Item RAW_KYBER_CRYSTAL =
            registerItem("raw_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item RED_KYBER_CRYSTAL =
            registerItem("red_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.RARE).fireproof()));
    public static final Item BLUE_KYBER_CRYSTAL =
            registerItem("blue_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.RARE).fireproof()));
    public static final Item GREEN_KYBER_CRYSTAL =
            registerItem("green_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.RARE).fireproof()));
    public static final Item PURPLE_KYBER_CRYSTAL =
            registerItem("purple_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.RARE).fireproof()));
    public static final Item YELLOW_KYBER_CRYSTAL =
            registerItem("yellow_kyber_crystal", new Item(new FabricItemSettings().maxCount(64).rarity(Rarity.RARE).fireproof()));


    public static final Lightsaber RED_LIGHTSABER = (Lightsaber) registerItem("red_lightsaber", new Lightsaber(7), true);
    public static final Lightsaber BLUE_LIGHTSABER = (Lightsaber) registerItem("blue_lightsaber", new Lightsaber(6), true);
    public static final Lightsaber GREEN_LIGHTSABER = (Lightsaber) registerItem("green_lightsaber", new Lightsaber(5), true);
    public static final Lightsaber PURPLE_LIGHTSABER = (Lightsaber) registerItem("purple_lightsaber", new Lightsaber(4), true);
    public static final Lightsaber YELLOW_LIGHTSABER = (Lightsaber) registerItem("yellow_lightsaber", new Lightsaber(3), true);



    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, new Identifier(LightsabersMod.MOD_ID, name), item);
    }

    private static Item registerItem(String name, Lightsaber item, boolean add_to_lightsaber) {
        if (!add_to_lightsaber) return registerItem(name, item);
        var res = Registry.register(Registries.ITEM, new Identifier(LightsabersMod.MOD_ID, name), item);
        lightsabers.add(res);

        return res;
    }

    public static void registerItems() {
        LightsabersMod.LOGGER.info("Registering Items" + LightsabersMod.sign());
    }
}
