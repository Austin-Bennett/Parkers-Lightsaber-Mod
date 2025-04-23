package austin.lightsabers;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class LightsabersSounds {

    public static final SoundEvent LIGHTSABER_HIT = registerSoundEvent("lightsaber_hit");
    public static final SoundEvent LIGHTSABER_SWING = registerSoundEvent("lightsaber_swing");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(LightsabersMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {
        LightsabersMod.LOGGER.info("Creating Sound events" + LightsabersMod.sign());

    }
}
