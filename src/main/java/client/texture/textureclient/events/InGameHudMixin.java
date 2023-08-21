package client.texture.textureclient.events;

import client.texture.textureclient.client.TextureClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        TextureClient.onRender(context,tickDelta,ci);
    }

}
