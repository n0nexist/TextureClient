package client.texture.textureclient.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class TextureModule {

    public boolean toggled = false;
    public String name = "";
    public String suffix = "";
    public TextureCategory category;

    public String keybind = "";

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public TextureModule(String name, String suffix, TextureCategory category, String keybind) {
        this.name = name;
        this.suffix = suffix;
        this.category = category;
        this.keybind = keybind;
    }

    public void toggle(){
        toggled = !toggled;
        if (toggled){
            mc.player.playSound(SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON, 3, 1);
        }else{
            mc.player.playSound(SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, 3, 1);
        }
    }

    public void onUpdate(){}

    public void onEnable(){}
    public void onDisable(){}

}
