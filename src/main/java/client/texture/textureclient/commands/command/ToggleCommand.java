package client.texture.textureclient.commands.command;

import client.texture.textureclient.client.TextureClient;
import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;

public class ToggleCommand extends TextureCommand {

    public ToggleCommand(){
        super("toggle", "modulename");
    }

    @Override
    public void runCommand(String[] args){
        TextureClient.modules.forEach((m)->{
            if (m.name.equalsIgnoreCase(args[0])){
                TextureUtils.togglemodule(m);
                TextureUtils.sendChatMessage("§f"+m.name+" §7toggled "+TextureUtils.boolToString(m.toggled).toLowerCase());
            }
        });
    }

}
