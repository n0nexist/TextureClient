package client.texture.textureclient.commands.command;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.modules.movement.Fly;

public class FlyCommand extends TextureCommand {

    public FlyCommand(){
        super("fly","flyspeed");
    }

    @Override
    public void runCommand(String[] args){
        try{
            Fly.flyspeed = Float.parseFloat(args[0]);
            TextureUtils.sendChatMessage("Fly speed is now set to §f"+Fly.flyspeed);
        }catch(Exception e){
            TextureUtils.sendChatMessage("Invalid number.");
        }

    }

}
