package client.texture.textureclient.modules.movement;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.command.TimerCommand;
import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import client.texture.textureclient.modules.misc.Timer;

public class Speed extends TextureModule {

    public static String mode = "bhop";
    public static float speed = 1.0f;

    public Speed(){
        super("Speed","", TextureCategory.MOVEMENT, "x");
    }

    @Override
    public void onUpdate(){
        if (mc.player == null){return;}

        this.suffix = mode;

        switch (mode.toLowerCase()){
            case "legit":
                if (mc.player.isOnGround() && mc.player.forwardSpeed>0){
                    mc.player.jump();
                }
                break;
            case "bhop":
                if (mc.player.forwardSpeed > 0) {
                    if (mc.player.isOnGround()) {
                        mc.player.jump();
                        TimerCommand.globalTimerFlag = true;
                        Timer.speed = 0.98f;
                    } else {
                        mc.player.forwardSpeed *= 0.98f;
                        Timer.speed = 1.4f;
                    }
                }
                break;
            case "motion":
                if (mc.player.isOnGround() && mc.player.forwardSpeed > 0) {
                    TextureUtils.strafeForward(speed);
                }
                break;
        }
    }

    @Override
    public void onDisable() {
        TimerCommand.globalTimerFlag = false;
        if (mc.player!=null) {
            mc.player.forwardSpeed = 0;
        }
    }
}
