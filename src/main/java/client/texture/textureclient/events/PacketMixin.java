package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;

@Mixin(ClientPlayNetworkHandler.class)
public class PacketMixin {

    @Inject(method = "sendPacket(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"))
    private void printReceivedPacket(Packet<?> packet, CallbackInfo ci) {
        if (TextureUtils.isModuleToggled("packetspy")) {
            TextureUtils.sendChatMessage("§aSending packet: §1" + packet.getClass().getName());
        }
    }

}

@Mixin(ClientConnection.class)
class PacketMixin2 {

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void printReceivedPacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (packet instanceof EntityVelocityUpdateS2CPacket && TextureUtils.isModuleToggled("antiknockback")){
            ci.cancel();
        }
        if (TextureUtils.isModuleToggled("packetspy")){
            TextureUtils.sendChatMessage("§bReceiving packet: §2" + packet.getClass().getName());
        }
    }

}
