package austin.lightsabers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;

public class networking {
    public static final Identifier SWING_MISS_PACKET = new Identifier(LightsabersMod.MOD_ID, "swing_miss");

    public static void sendSwingMissPacket() {
        ClientPlayNetworking.send(SWING_MISS_PACKET, PacketByteBufs.empty());
    }

    public static void registerServerReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(SWING_MISS_PACKET, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                // Run your logic here!
                player.getWorld().playSound(null, player.getBlockPos(), LightsabersSounds.LIGHTSABER_SWING
                , SoundCategory.PLAYERS, 1, 1);
            });
        });
    }
}
