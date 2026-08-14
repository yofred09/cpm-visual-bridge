package com.yofred.cpmvisualbridge.mixin.mowzies;

import com.bobmowzie.mowziesmobs.client.model.entity.ModelPlayerAnimated;
import com.bobmowzie.mowziesmobs.client.render.entity.player.GeckoPlayer;
import com.bobmowzie.mowziesmobs.client.render.entity.player.GeckoRenderPlayer;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yofred.cpmvisualbridge.compat.CpmCompat;
import com.yofred.cpmvisualbridge.compat.CpmSoftBridge;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import software.bernie.geckolib.cache.object.BakedGeoModel;

/** Uses Mowzie's prepared ability pose as the parent transform for CPM geometry. */
@Mixin(GeckoRenderPlayer.class)
public abstract class GeckoRenderPlayerMixin {
    @WrapOperation(
            method = "actuallyRender",
            at = @At(
                    value = "INVOKE",
                    target = "Lsoftware/bernie/geckolib/renderer/GeoRenderer;actuallyRender(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/Object;Lsoftware/bernie/geckolib/cache/object/BakedGeoModel;Lnet/minecraft/client/renderer/RenderType;Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZFIII)V"
            ),
            require = 0
    )
    private void cpmvisualbridge$renderAnimatedCpmPlayer(
            Object renderer,
            PoseStack poseStack,
            Object animatableObject,
            BakedGeoModel bakedModel,
            RenderType renderType,
            MultiBufferSource buffers,
            VertexConsumer consumer,
            boolean isReRender,
            float partialTick,
            int packedLight,
            int packedOverlay,
            int color,
            Operation<Void> original
    ) {
        if (!(animatableObject instanceof GeckoPlayer animatable)
                || !(animatable.getPlayer() instanceof AbstractClientPlayer player)
                || !Boolean.TRUE.equals(CpmCompat.hasCustomModel(player))) {
            original.call(renderer, poseStack, animatableObject, bakedModel, renderType, buffers,
                    consumer, isReRender, partialTick, packedLight, packedOverlay, color);
            return;
        }

        GeckoRenderPlayer mowzieRenderer = (GeckoRenderPlayer) (Object) this;
        PlayerModel<AbstractClientPlayer> playerModel = mowzieRenderer.getModel();
        ModelPlayerAnimated.copyFromGeckoModel(playerModel, mowzieRenderer.getGeckoModel());

        if (!CpmSoftBridge.renderPlayerModelSafe(
                player, playerModel, poseStack, consumer, buffers, packedLight, packedOverlay, color
        )) {
            original.call(renderer, poseStack, animatableObject, bakedModel, renderType, buffers,
                    consumer, isReRender, partialTick, packedLight, packedOverlay, color);
        }
    }
}
