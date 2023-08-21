package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static client.texture.textureclient.client.TextureClient.mc;
import static client.texture.textureclient.modules.render.Nametags.scale;
import static org.lwjgl.opengl.GL11.*;

@Mixin(EntityRenderer.class)
public abstract class NametagsMixin<T extends Entity> {

    int color = new Color(6, 205, 205).getRGB();

    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"), cancellable = true)
    private void renderLabelIfPresent(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci){

        if (mc.player == null){return;}
        if (!TextureUtils.isModuleToggled("nametags")){return;}

        try {
            text = Text.literal(text.getString() + " | " + ((LivingEntity) entity).getHealth());
        }catch(Exception e){}

        double d = mc.getEntityRenderDispatcher().getSquaredDistanceToCamera(entity);

        if (!(d > 4096.0)) {
            float f = entity.getNameLabelHeight();
            int i = "deadmau5".equals(text.getString()) ? -10 : 0;
            matrices.push();
            matrices.translate(0.0F, f, 0.0F);
            matrices.multiply(mc.getEntityRenderDispatcher().getRotation());
            matrices.scale((-0.025F)*scale, (-0.025F)*scale, (0.025F)*scale);
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F*3);
            int j = (int)(g * 255.0F) << 24;
            float h = (float)(-mc.textRenderer.getWidth(text) / 2);
            mc.textRenderer.draw(text, h, (float)i, color, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, j, light);
            mc.textRenderer.draw(text, h, (float)i, color, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
            matrices.pop();
        }

        ci.cancel();

    }


}


