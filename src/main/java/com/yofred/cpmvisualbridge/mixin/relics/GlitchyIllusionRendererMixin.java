package com.yofred.cpmvisualbridge.mixin.relics;

import com.yofred.cpmvisualbridge.BridgeClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yofred.cpmvisualbridge.compat.CpmCompat;
import com.yofred.cpmvisualbridge.compat.CpmSoftBridge;
import com.yofred.cpmvisualbridge.compat.PehkuiScaleBridge;
import it.hurts.sskirillss.relics.client.renderer.entities.GlitchyIllusionRenderer;
import it.hurts.sskirillss.relics.entities.GlitchyIllusionEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/** Renders Relics' player illusion through CPM while retaining its original passes and colours. */
@Mixin(GlitchyIllusionRenderer.class)
public abstract class GlitchyIllusionRendererMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/PlayerModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V",
                    ordinal = 0
            ),
            require = 0
    )
    private void cpmvisualbridge$renderCpmBody(
            PlayerModel<?> model,
            PoseStack poseStack,
            VertexConsumer consumer,
            int packedLight,
            int packedOverlay,
            int color,
            GlitchyIllusionEntity illusion,
            float entityYaw,
            float partialTicks,
            PoseStack originalPoseStack,
            MultiBufferSource buffers,
            int originalPackedLight
    ) {
        cpmvisualbridge$renderPass(model, poseStack, consumer, packedLight, packedOverlay, color,
                illusion, partialTicks, buffers);
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/PlayerModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V",
                    ordinal = 1
            ),
            require = 0
    )
    private void cpmvisualbridge$renderCpmAura(
            PlayerModel<?> model,
            PoseStack poseStack,
            VertexConsumer consumer,
            int packedLight,
            int packedOverlay,
            int color,
            GlitchyIllusionEntity illusion,
            float entityYaw,
            float partialTicks,
            PoseStack originalPoseStack,
            MultiBufferSource buffers,
            int originalPackedLight
    ) {
        cpmvisualbridge$renderPass(model, poseStack, consumer, packedLight, packedOverlay, color,
                illusion, partialTicks, buffers);
    }

    private static void cpmvisualbridge$renderPass(
            PlayerModel<?> model,
            PoseStack poseStack,
            VertexConsumer consumer,
            int packedLight,
            int packedOverlay,
            int color,
            GlitchyIllusionEntity illusion,
            float partialTicks,
            MultiBufferSource buffers
    ) {
        if (!BridgeClientConfig.RELICS.get()
                || !(illusion.getOwner() instanceof AbstractClientPlayer player)) {
            model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, color);
            return;
        }

        float width = PehkuiScaleBridge.modelWidth(player, partialTicks);
        float height = PehkuiScaleBridge.modelHeight(player, partialTicks);
        poseStack.pushPose();
        try {
            poseStack.scale(width, height, width);
            if (!CpmCompat.isApiAvailable() || !CpmSoftBridge.renderPlayerModelSafe(
                    player, model, poseStack, consumer, buffers, packedLight, packedOverlay, color
            )) {
                model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, color);
            }
        } finally {
            poseStack.popPose();
        }
    }
}
