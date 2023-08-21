package client.texture.textureclient.events;


import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(EntityRenderer.class)
public abstract class TestingMixin<T extends Entity> {



}


