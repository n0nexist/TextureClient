package client.texture.textureclient.utils;

import client.texture.textureclient.client.ClientInfo;
import client.texture.textureclient.client.TextureClient;
import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import static client.texture.textureclient.client.TextureClient.mc;

public class TextureUtils {

    public static void teleport(int len){
        if (mc.player == null){return;}
        if (mc.world == null){return;}

        double yaw = mc.player.getYaw() * 0.017453292;
        double pitch = mc.player.getPitch() * 0.017453292;
        BlockPos p = mc.player.getBlockPos();

        double x = p.getX() + -Math.sin(yaw) * len;
        double y = p.getY() + -Math.sin(pitch) * len;
        double z = p.getZ() + Math.cos(yaw) * len;

        if (mc.world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock().equals(Blocks.AIR)){
            mc.player.setPosition(x,y+1,z);
        }else{
            teleport(len-1);
        }
    }

    public static float roundNumber(float n, int places) {
        float scale = (float) Math.pow(10, places);
        return Math.round(n * scale) / scale;
    }


    public static void sendChatMessage(String text){
        if (mc.player != null) {
            mc.player.sendMessage(Text.literal("§f§l[§f§b"+ ClientInfo.getClientName()+"§f§l]§7 "+text), false);
        }
    }

    public static String boolToString(boolean a){
        return ((a)?"§aOn":"§cOff");
    }

    public static void sendMessage(String text){
        if (mc.player != null) {
            mc.player.sendMessage(Text.literal(text), true);
        }
    }

    public static boolean isModuleToggled(String module){
        for (TextureModule m : TextureClient.modules){
            if (m.name.equalsIgnoreCase(module)){
                return m.toggled;
            }
        }
        return false;
    }

    public static void vclip(int len){
        if (mc.player==null){return;}
        BlockPos p = mc.player.getBlockPos();
        mc.player.setPosition(p.getX(),p.getY()+len,p.getZ());
    }

    public static void hclip(int len) {
        if (mc.player == null) {
            return;
        }
        double yawRad = Math.toRadians(mc.player.getYaw());
        double xOffset = -Math.sin(yawRad) * len;
        double zOffset = Math.cos(yawRad) * len;
        BlockPos p = mc.player.getBlockPos();
        mc.player.setPosition(p.getX() + xOffset, p.getY(), p.getZ() + zOffset);
    }

    public static void strafeForward(float len) {
        if (mc.player == null) {
            return;
        }
        double yawRad = Math.toRadians(mc.player.bodyYaw);
        double xOffset = -Math.sin(yawRad) * len;
        double zOffset = Math.cos(yawRad) * len;
        BlockPos p = mc.player.getBlockPos();
        strafeForward_(p.getX() + xOffset, p.getZ() + zOffset, len);
    }

    public static void strafeForward_(double targetX, double targetZ, double speed) {
        if (mc.player == null) {
            return;
        }

        double currentX = mc.player.getX();
        double currentZ = mc.player.getZ();
        double deltaX = targetX - currentX;
        double deltaZ = targetZ - currentZ;
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        if (distance > 0.0) {
            double strafeX = deltaX / distance;
            double strafeZ = deltaZ / distance;

            mc.player.addVelocity(strafeX * speed, mc.player.getVelocity().y, strafeZ * speed);
        }
    }


    public static void togglemodule(TextureModule m){
        m.toggle();
        TextureUtils.sendMessage(m.name + ": " + TextureUtils.boolToString(m.toggled));
        if (m.toggled) {
            m.onEnable();
        } else {
            m.onDisable();
        }
    }


    public static void toggle(String buttonText) {
        for (TextureModule x : TextureClient.modules){
            if (x.name.equalsIgnoreCase(buttonText)){
                togglemodule(x);
            }
        }
    }

    public static ArrayList<TextureModule> getModulesByCategory(TextureCategory c) {
        ArrayList<TextureModule> modules = new ArrayList<>();
        TextureClient.modules.forEach((m)->{
            if (m.category==c){
                modules.add(m);
            }
        });
        return modules;
    }

    public static ArrayList<TextureModule> getModulesInAlphabeticalOrder() {
        ArrayList<TextureModule> temp = new ArrayList<>(TextureClient.modules);
        ArrayList<TextureModule> sortedTemp = new ArrayList<>(temp);
        Collections.sort(sortedTemp, Comparator.comparing(module -> module.name));
        return sortedTemp;
    }

    public static ArrayList<TextureModule> getModulesInLengthOrder() {
        ArrayList<TextureModule> temp = new ArrayList<>(TextureClient.modules);
        ArrayList<TextureModule> sortedTemp = new ArrayList<>(temp);
        Collections.sort(sortedTemp, new Comparator<TextureModule>() {
            @Override
            public int compare(TextureModule a1, TextureModule a2) {
                String a = "| "+a1.name+(!Objects.equals(a1.suffix, "") ?" ["+a1.suffix+"]":"");
                String b = "| "+a2.name+(!Objects.equals(a2.suffix, "") ?" ["+a2.suffix+"]":"");
                int lengthComparison = Integer.compare(b.length(), a.length());
                if (lengthComparison != 0) {
                    return lengthComparison;
                }
                return a.compareTo(b);
            }
        });
        return sortedTemp;
    }


}
