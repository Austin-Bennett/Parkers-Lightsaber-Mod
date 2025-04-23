package austin.lightsabers.mixin;

import austin.lightsabers.LightsabersSounds;
import austin.lightsabers.item.Lightsaber;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AxeBypassMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (source.getAttacker() instanceof LivingEntity && entity.isBlocking() && entity.getActiveItem().getItem() instanceof Lightsaber
        && (!source.isIn(DamageTypeTags.BYPASSES_SHIELD) &&
                !(((LivingEntity)source.getAttacker()).getActiveItem().getItem() instanceof AxeItem))) {
            // Ignore axe disabling if using your custom shield
            cir.setReturnValue(false); // cancels damage
            entity.getWorld().playSound(null, entity.getBlockPos(),
                    LightsabersSounds.LIGHTSABER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            cir.cancel();
        }
    }
}

