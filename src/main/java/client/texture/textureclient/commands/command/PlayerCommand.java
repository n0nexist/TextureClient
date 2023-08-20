package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;

import static client.texture.textureclient.client.TextureClient.mc;

public class PlayerCommand extends TextureCommand {

    public PlayerCommand(){
        super("player","none");
    }

    @Override
    public void runCommand(String[] args){
        TextureUtils.sendChatMessage("Name: §f"+mc.player.getName());
        TextureUtils.sendChatMessage("UUID: §f"+mc.player.getUuid().toString());
        TextureUtils.sendChatMessage("Gamemode: §f"+mc.interactionManager.getCurrentGameMode().toString());
    }

}
