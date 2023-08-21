package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.commands.TextureCommandManager;

import java.util.Collections;
import java.util.Comparator;

public class HelpCommand extends TextureCommand {

    public HelpCommand(){
        super("help","none");
    }

    @Override
    public void runCommand(String[] args){
        TextureUtils.sendChatMessage("Commands:");

        for (TextureCommand x : TextureCommandManager.commands){
            TextureUtils.sendChatMessage("§f|§7 "+x.name+"§f,§7 args=§b"+x.argList);
        }
    }

}
