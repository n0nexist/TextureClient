package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.command.TimerCommand;
import client.texture.textureclient.modules.misc.Timer;
import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.class)
public class RenderTickMixin {
    @Shadow public float lastFrameDuration;

    @Inject(method = "beginRenderTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode = Opcodes.PUTFIELD))
    private void onBeingRenderTick(long a, CallbackInfoReturnable<Integer> info) {
        if (TextureUtils.isModuleToggled("timer") || TimerCommand.globalTimerFlag) {
            lastFrameDuration *= Timer.speed;
        }else{
            lastFrameDuration *= 1;
        }
    }
}
