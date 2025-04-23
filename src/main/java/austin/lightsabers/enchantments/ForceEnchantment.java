package austin.lightsabers.enchantments;

import austin.lightsabers.item.LightsaberBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ForceEnchantment extends Enchantment {
    protected ForceEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return 3*level;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof LightsaberBase;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
