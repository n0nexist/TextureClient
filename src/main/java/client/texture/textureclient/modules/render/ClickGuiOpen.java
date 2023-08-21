package client.texture.textureclient.modules.render;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import client.texture.textureclient.ui.ClickGui;
import net.minecraft.text.Text;

public class ClickGuiOpen extends TextureModule {

    public ClickGuiOpen(){
        super("ClickGui", "", TextureCategory.RENDER, "right.shift");
    }

    @Override
    public void onEnable(){
        mc.setScreen(new ClickGui());
        this.toggle();
    }
}
