package client.texture.textureclient.commands.command;

import client.texture.textureclient.commands.TextureCommand;
import client.texture.textureclient.events.NametagsMixin;
import client.texture.textureclient.modules.movement.Fly;
import client.texture.textureclient.modules.render.Nametags;
import client.texture.textureclient.utils.TextureUtils;

public class NametagsCommand extends TextureCommand {

    public NametagsCommand(){
        super("nametags","scale");
    }

    @Override
    public void runCommand(String[] args){
        try{
            Nametags.scale = Float.parseFloat(args[0]);
            TextureUtils.sendChatMessage("Nametags scale is now set to §f"+Nametags.scale);
        }catch(Exception e){
            TextureUtils.sendChatMessage("Invalid number.");
        }

    }

}
