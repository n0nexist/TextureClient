package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class CustomPayloadMixin {

    @Inject(method = "onCustomPayload", at = @At("HEAD"))
    private void onCustomPayload(CustomPayloadS2CPacket packet, CallbackInfo ci) {
        TextureUtils.sendChatMessage("CustomPayload Detected:");
        TextureUtils.sendChatMessage("§fChannel: §7"+packet.getChannel().toString());
        TextureUtils.sendChatMessage("§fData: §7"+packet.getData().readString()+"\n");
    }

}
