package client.texture.textureclient.events;

import client.texture.textureclient.client.ClientInfo;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static client.texture.textureclient.client.TextureClient.mc;

@Mixin(MinecraftClient.class)
public abstract class WindowTitleMixin {

    @Inject(method = "updateWindowTitle", at = @At("TAIL"))
    private void updateWindowTitle(CallbackInfo ci) {
        mc.getWindow().setTitle("Running "+ ClientInfo.getFullClientName());
    }

}
