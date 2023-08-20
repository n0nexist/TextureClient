package client.texture.textureclient.modules.render;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Fullbright extends TextureModule {


    public Fullbright(){
        super("Fullbright", "", TextureCategory.RENDER, "b");
    }

    @Override
    public void onEnable(){
        
        mc.player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.NIGHT_VISION,
                Integer.MAX_VALUE,
                1,
                false,
                false
        ));
    }

    @Override
    public void onDisable(){
        
        mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }

}
