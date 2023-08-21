package client.texture.textureclient.modules.movement;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.util.math.BlockPos;

public class Fly extends TextureModule {

    public static float flyspeed = 0.5f;

    public Fly(){
        super("Fly", "", TextureCategory.MOVEMENT, "g");
    }

    @Override
    public void onEnable(){
        mc.player.getAbilities().allowFlying = true;
        BlockPos p = mc.player.getBlockPos();
        mc.player.setPosition(p.getX(),p.getY()+0.5,p.getZ());
    }

    @Override
    public void onUpdate(){
        if (mc.player == null){return;}
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(flyspeed);
        this.suffix = String.valueOf(flyspeed);
    }

    @Override
    public void onDisable(){
        mc.player.getAbilities().flying = false;
        mc.player.getAbilities().allowFlying = false;
    }

}
