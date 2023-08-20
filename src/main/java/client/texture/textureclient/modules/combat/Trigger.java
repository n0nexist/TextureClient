package client.texture.textureclient.modules.combat;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.hit.HitResult;

public class Trigger extends TextureModule {


    public Trigger(){
        super("Trigger", "", TextureCategory.COMBAT, "c");
    }

    @Override
    public void onUpdate(){
        HitResult hitResult = mc.crosshairTarget;
        if (hitResult==null){return;}
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            KeyBinding.onKeyPressed(mc.options.attackKey.getDefaultKey());
        }
    }



}
