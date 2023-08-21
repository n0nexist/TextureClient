package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.modules.combat.Criticals;

import java.util.Arrays;

public class CriticalsCommand extends TextureCommand {

    String[] modes = new String[]{"jump", "hop", "lowhop"};

    public CriticalsCommand(){
        super("criticals","mode");
    }

    @Override
    public void runCommand(String[] args){
        String x = args[0];
        boolean flag = false;
        for (String temp : modes){
            if (x.equalsIgnoreCase(temp)){
                Criticals.mode = x;
                TextureUtils.sendChatMessage("Criticals mode is now set to §f"+x);
                flag = true;
            }
        }
        if (!flag){
            TextureUtils.sendChatMessage("Invalid mode. valid modes:\n§f"+ Arrays.toString(modes));
        }
    }

}
