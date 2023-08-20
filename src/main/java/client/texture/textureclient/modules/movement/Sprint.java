package client.texture.textureclient.modules.movement;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;

public class Sprint extends TextureModule {

    public Sprint(){
        super("Sprint","", TextureCategory.MOVEMENT, "z");
    }

    @Override
    public void onUpdate(){
        if (mc.player == null){return;}
        mc.player.setSprinting(true);
    }

}
