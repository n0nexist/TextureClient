package client.texture.textureclient.client;

import client.texture.textureclient.commands.TextureCommandManager;
import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import client.texture.textureclient.modules.combat.*;
import client.texture.textureclient.modules.misc.*;
import client.texture.textureclient.modules.movement.*;
import client.texture.textureclient.modules.render.*;
import client.texture.textureclient.ui.ClickGui;
import client.texture.textureclient.utils.TextureUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import net.minecraft.client.gui.screen.ChatScreen;

public class TextureClient implements ClientModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static ArrayList<TextureModule> modules = new ArrayList<TextureModule>();

    @Override
    public void onInitializeClient() {
        System.out.println("INITIALIZING "+ClientInfo.getFullClientName());

        modules.add(new Sprint());
        modules.add(new Fly());
        modules.add(new NoFall());
        modules.add(new Fullbright());
        modules.add(new Test());
        modules.add(new NoRain());
        modules.add(new Trigger());
        modules.add(new Criticals());
        modules.add(new Speed());
        modules.add(new Killaura());
        modules.add(new NoSwing());
        modules.add(new Timer());
        modules.add(new Chams());
        modules.add(new Jesus());
        modules.add(new Nametags());
        modules.add(new NoPlayerPush());
        modules.add(new AntiKnockback());
        modules.add(new PacketSpy());
        modules.add(new ClickGuiOpen());

        TextureCommandManager.loadCommands();

        ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);

        System.out.println(ClientInfo.getFullClientName()+" INITIALIZED");
    }

    public static void onKeyPress(String key) {

        if (mc.currentScreen instanceof ChatScreen || mc.currentScreen instanceof ClickGui || mc.player == null){
            return;
        }

        modules.forEach((m)->{
            if (!Objects.equals(m.keybind, "none")) {
                if (("key.keyboard." + m.keybind).equalsIgnoreCase(key)) {
                    TextureUtils.togglemodule(m);
                }
            }
        });
    }

    public void onClientTick(MinecraftClient client){
        modules.forEach((m)->{
            if (m.toggled){
                m.onUpdate();
            }
        });
    }

    public static void drawContext(DrawContext context, String stri, int offset){
        context.drawTextWithShadow(mc.textRenderer, stri, 3, mc.textRenderer.fontHeight - 3 + offset, -1);
    }

    public static void onRender(DrawContext context, float tickDelta, CallbackInfo ci){

        int offset = 0;

        String stri = "§7"+ClientInfo.getClientName()+" §b"+ClientInfo.getClientVersion();
        drawContext(context,stri,offset);
        offset += mc.textRenderer.fontHeight;

        stri = "§7FPS: §b"+mc.getCurrentFps();
        drawContext(context,stri,offset);
        offset += mc.textRenderer.fontHeight;

        for (TextureModule m : TextureUtils.getModulesInLengthOrder()){
            if (m.toggled) {
                stri = "§f| §b"+m.name+(!Objects.equals(m.suffix, "") ?"§7 ["+m.suffix+"]§f":"");
                drawContext(context, stri, offset);
                offset += mc.textRenderer.fontHeight;
            }
        }

    }

}