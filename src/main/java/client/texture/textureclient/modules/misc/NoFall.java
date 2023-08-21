package client.texture.textureclient.modules.misc;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;

public class NoFall extends TextureModule {

    public NoFall(){
        super("NoFall","", TextureCategory.MISC, "i");
    }

    @Override
    public void onUpdate(){
        if (mc.player == null){return;}
        if (mc.player.fallDistance>2){
            mc.getNetworkHandler().sendPacket(new OnGroundOnly(true));
        }
    }

}
