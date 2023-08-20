package client.texture.textureclient.events;

import client.texture.textureclient.commands.TextureCommandManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ClientPlayNetworkHandler.class)
public class ChatMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(String message, CallbackInfo ci) {
        try {
            if (message.startsWith("#")) {
                TextureCommandManager.processCommand(message.split("#")[1].split(" ")[0], message.split(message.split(" ")[0]));
                ci.cancel();
            }
        }catch(Exception e){}
    }
}

