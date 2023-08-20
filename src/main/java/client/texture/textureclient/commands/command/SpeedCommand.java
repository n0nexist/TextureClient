package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.modules.movement.Speed;

import java.util.Arrays;

public class SpeedCommand extends TextureCommand {

    String[] modes = new String[]{"legit", "motion", "bhop"};

    public SpeedCommand(){
        super("speed","mode");
    }

    @Override
    public void runCommand(String[] args){
        String x = args[0];
        boolean flag = false;
        for (String temp : modes){
            if (x.equalsIgnoreCase(temp)){
                Speed.mode = x;
                TextureUtils.sendChatMessage("Speed mode is now set to §f"+x);
                flag = true;
            }
        }
        if (!flag){
            TextureUtils.sendChatMessage("Invalid mode. valid modes:\n§f" + Arrays.toString(modes));
        }


    }

}
