package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.modules.combat.Criticals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static client.texture.textureclient.client.TextureClient.mc;

@Mixin(PlayerEntity.class)
public class AttackMixin {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void onAttack(Entity target, CallbackInfo ci) {
        if (TextureUtils.isModuleToggled("criticals")) {

            if (!mc.player.isOnGround()){return;}

            switch(Criticals.mode.toLowerCase()){
                case "jump":
                    mc.player.jump();
                    break;
                case "hop":
                    mc.player.setVelocity(0,0.3425,0);
                    mc.player.setVelocity(0,0.3425,0);
                    break;
                case "lowhop":
                    mc.player.setVelocity(0,0.1,0);
                    mc.player.setVelocity(0,0.1,0);
                    mc.player.setOnGround(false);
                    break;
            }

        }
    }
}

