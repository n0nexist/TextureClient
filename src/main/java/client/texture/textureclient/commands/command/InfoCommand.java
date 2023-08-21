package client.texture.textureclient.commands.command;

import client.texture.textureclient.client.ClientInfo;
import client.texture.textureclient.client.TextureClient;
import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.commands.TextureCommandManager;

public class InfoCommand extends TextureCommand {

    public InfoCommand(){
        super("info","none");
    }

    @Override
    public void runCommand(String[] args){
        TextureUtils.sendChatMessage("Running: §f"+ ClientInfo.getFullClientName());
        TextureUtils.sendChatMessage("Loaded Modules: §f"+ TextureClient.modules.size());
        TextureUtils.sendChatMessage("Loaded Commands: §f"+ TextureCommandManager.commands.size());
    }

}
