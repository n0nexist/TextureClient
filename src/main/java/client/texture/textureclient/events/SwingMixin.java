package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class SwingMixin {

    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = true)
    private void onSwing(Hand hand, CallbackInfo ci) {
        if (TextureUtils.isModuleToggled("noswing")) {
            ci.cancel();
        }
    }
}

