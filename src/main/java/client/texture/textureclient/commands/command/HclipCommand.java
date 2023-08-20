package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import net.minecraft.util.math.BlockPos;

import static client.texture.textureclient.client.TextureClient.mc;

public class HclipCommand extends TextureCommand {

    public HclipCommand(){
        super("hclip","blocks");
    }

    @Override
    public void runCommand(String[] args){
        try {
            TextureUtils.hclip(Integer.parseInt(args[0]));
            BlockPos p = mc.player.getBlockPos();
            TextureUtils.sendChatMessage("Teleported to §f"+p.getX()+"§7/§f"+p.getY()+"§7/§f"+p.getZ());
        }catch(Exception e){
            TextureUtils.sendChatMessage("Invalid number.");
        }
    }

}
