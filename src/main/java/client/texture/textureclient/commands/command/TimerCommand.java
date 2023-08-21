package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.modules.misc.Timer;

public class TimerCommand extends TextureCommand {

    public static boolean globalTimerFlag = false;

    public TimerCommand(){
        super("timer","speed");
    }

    @Override
    public void runCommand(String[] args){
        try {
            Timer.speed = Float.parseFloat(args[0]);
            TextureUtils.sendChatMessage("Timer speed is now set to §f"+Timer.speed);
        }catch(Exception e){
            TextureUtils.sendChatMessage("Invalid number.");
        }
    }

}
