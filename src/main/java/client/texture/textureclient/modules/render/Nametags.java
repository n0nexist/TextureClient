package client.texture.textureclient.modules.render;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;

public class Nametags extends TextureModule {

    public static float scale = 2.2f;

    public Nametags(){
        super("Nametags", "", TextureCategory.RENDER, "none");
    }

    @Override
    public void onUpdate() {
        this.suffix = scale+"f";
    }
}
