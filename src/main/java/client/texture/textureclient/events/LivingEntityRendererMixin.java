package client.texture.textureclient.events;

import client.texture.textureclient.utils.TextureUtils;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static org.lwjgl.opengl.GL11.*;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    private void renderHead(LivingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (TextureUtils.isModuleToggled("chams")) {
            glEnable(GL_POLYGON_OFFSET_FILL);
            glPolygonOffset(1.0f, -1100000.0f);
        }
    }

    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    private void renderTail(LivingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (TextureUtils.isModuleToggled("chams")) {
            glPolygonOffset(1.0f, 1100000.0f);
            glDisable(GL_POLYGON_OFFSET_FILL);
        }
    }

}

