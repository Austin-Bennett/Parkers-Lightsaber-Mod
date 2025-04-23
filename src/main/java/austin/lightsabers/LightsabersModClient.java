package austin.lightsabers;

import austin.lightsabers.item.LightsaberBase;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.hit.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightsabersModClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
			if (minecraftClient.player == null) return;
			if (minecraftClient.player.getMainHandStack().getItem() instanceof LightsaberBase &&
					minecraftClient.player.handSwingProgress < 0.3 && minecraftClient.player.handSwingProgress > 0 && minecraftClient.crosshairTarget != null) {
				if (minecraftClient.crosshairTarget.getType() == HitResult.Type.MISS) {
					networking.sendSwingMissPacket();
				}
			}
		});
	}
}