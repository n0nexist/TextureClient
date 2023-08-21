package client.texture.textureclient.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.ActionResult;

public interface KeyInputEvent {
    Event<KeyInputEvent> EVENT = EventFactory.createArrayBacked(KeyInputEvent.class,
            (listeners) -> (key) -> {
                for (KeyInputEvent listener : listeners) {
                    ActionResult result = listener.onKeyInput(key);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult onKeyInput(InputUtil.Key key);
}

