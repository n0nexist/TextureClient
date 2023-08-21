package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.WorldRenderer;


@Mixin(WorldRenderer.class)
public abstract class NoRainMixin {

    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    private void cancelRainRendering(CallbackInfo ci) {
        cancelEvent(ci);
    }

    @Inject(method = "tickRainSplashing", at = @At("HEAD"), cancellable = true)
    private void cancelRainSplash(CallbackInfo ci) {
        cancelEvent(ci);
    }

    @Unique
    private void cancelEvent(CallbackInfo ci){
        if (TextureUtils.isModuleToggled("norain")){
            ci.cancel();
        }
    }

}
