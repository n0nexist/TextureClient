package client.texture.textureclient.modules.misc;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;

public class Timer extends TextureModule {

    public static float speed = 2;

    public Timer(){
        super("Timer","", TextureCategory.MISC, "k");
    }

    @Override
    public void onUpdate(){
        this.suffix = String.valueOf(speed);
    }

}
