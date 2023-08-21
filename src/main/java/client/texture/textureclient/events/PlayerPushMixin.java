package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public class PlayerPushMixin {

    @Inject(method = "addVelocity(DDD)V", at = @At("HEAD"), cancellable = true)
    private void antiknockback(double x, double y, double z, CallbackInfo ci) {
        cancelif(ci);
    }

    @Inject(method = "addVelocity(Lnet/minecraft/util/math/Vec3d;)V", at = @At("HEAD"), cancellable = true)
    private void antiknockback2(Vec3d velocity, CallbackInfo ci) {
        cancelif(ci);
    }

    @Unique
    private void cancelif(CallbackInfo ci){
        if (TextureUtils.isModuleToggled("noplayerpush")){
            ci.cancel();
        }
    }

}

