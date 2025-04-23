package austin.lightsabers;

import austin.lightsabers.enchantments.LightsaberEnchantments;
import austin.lightsabers.item.Lightsaber;
import austin.lightsabers.item.LightsaberBase;
import austin.lightsabers.item.LightsaberItemGroup;
import austin.lightsabers.item.LightsaberItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightsabersMod implements ModInitializer {

	public static final String MOD_ID = "lightsabers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Lightsabers Mod" + sign());
		LightsaberItems.registerItems();
		LightsaberItemGroup.register();
		LightsabersSounds.register();
		LightsaberEnchantments.registerEnchantments();


		networking.registerServerReceiver();
	}

	public static String sign() {
		return " [" + MOD_ID + "]";
	}
}