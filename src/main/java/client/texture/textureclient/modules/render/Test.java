package client.texture.textureclient.modules.render;

import client.texture.textureclient.utils.TimeHelper;
import client.texture.textureclient.commands.command.TimerCommand;
import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import client.texture.textureclient.modules.misc.Timer;

public class Test extends TextureModule {

    TimeHelper t = new TimeHelper();

    public Test(){
        super("Test", "", TextureCategory.RENDER, "none");
    }


    @Override
    public void onEnable() {
        t.reset();
        Timer.speed = 4;
    }

    @Override
    public void onUpdate() {

       if (t.hasTimePassed(3000)){
           t.reset();
           TimerCommand.globalTimerFlag = !TimerCommand.globalTimerFlag;
       }


    }
}
