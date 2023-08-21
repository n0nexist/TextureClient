package client.texture.textureclient.modules.combat;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;

public class Criticals extends TextureModule {

    public static String mode = "jump";

    public Criticals(){
        super("Criticals", "", TextureCategory.COMBAT, "none");
    }

    @Override
    public void onUpdate(){
        this.suffix = mode;
    }


}
