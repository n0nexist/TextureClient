package client.texture.textureclient.events;

import client.texture.textureclient.client.TextureClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    ArrayList<String> pressedKeys = new ArrayList<>();

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onOnKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        String inputKey = String.valueOf(InputUtil.fromKeyCode(key, scancode));

        if (pressedKeys.contains(inputKey)){
            pressedKeys.remove(inputKey);
        }else{
            pressedKeys.add(inputKey);
            TextureClient.onKeyPress(inputKey);
        }

    }
}
