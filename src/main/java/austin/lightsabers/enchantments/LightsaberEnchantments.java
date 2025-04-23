package austin.lightsabers.enchantments;

import austin.lightsabers.LightsabersMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LightsaberEnchantments {

    public static final Enchantment FORCE = registerEnchantment("force_enchant", new ForceEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.BREAKABLE,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));

    public static Enchantment registerEnchantment(String name, Enchantment E) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(LightsabersMod.MOD_ID, name), E);
    }

    public static void registerEnchantments() {

        LightsabersMod.LOGGER.info("Registering enchantments" + LightsabersMod.sign());
    }
}
