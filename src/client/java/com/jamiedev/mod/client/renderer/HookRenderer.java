package com.jamiedev.mod.client.renderer;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.entities.projectile.HookEntity;
import com.jamiedev.mod.init.JamiesModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.*;
import org.joml.Matrix4f;

public class HookRenderer extends EntityRenderer<HookEntity>
{
    private static final Identifier TEXTURE = JamiesMod.getModId("textures/entity/hook.png");
    public HookRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }
    public void render(HookEntity hook, float f, float g, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int i) {
        PlayerEntity player = hook.getPlayerOwner();
        if (player != null) {
            poseStack.push();
            Vec3d vec3 = getPlayerHandPos(player, g, JamiesModItems.HOOK, this.dispatcher);
            Vec3d vec32 = new Vec3d(
             MathHelper.lerp(g, hook.lastRenderX, hook.getX()), 
            MathHelper.lerp(g, hook.lastRenderY, hook.getY()) + (double)hook.getStandingEyeHeight(),
            MathHelper.lerp(g, hook.lastRenderZ, hook.getZ()));
            
            
            float h = 0;
            float j = h * 0.15F % 1.0F;
            Vec3d vec33 = vec3.subtract(vec32);
            float k = (float)(vec33.length() + 0.1);
            vec33 = vec33.normalize();
            float l = (float)Math.acos(vec33.y);
            float m = (float)Math.atan2(vec33.z, vec33.x);
            poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((1.5707964F - m) * 57.295776F));
            poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(l * 57.295776F));
            float n = h * 0.05F * -1.5F;
            float p = MathHelper.cos(n + 3.1415927F) * 0.2F;
            float q = MathHelper.sin(n + 3.1415927F) * 0.2F;
            float r = MathHelper.cos(n + 0.0F) * 0.2F;
            float s = MathHelper.sin(n + 0.0F) * 0.2F;
            float t = MathHelper.cos(n + 1.5707964F) * 0.2F;
            float u = MathHelper.sin(n + 1.5707964F) * 0.2F;
            float v = MathHelper.cos(n + 4.712389F) * 0.2F;
            float w = MathHelper.sin(n + 4.712389F) * 0.2F;
            float aa = -1.0F + j;
            float ab = k * 2.5F + aa;
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(
                    RenderLayer.getEntityCutout(TEXTURE));
            MatrixStack.Entry pose = poseStack.peek();
            Matrix4f matrix4f = pose.getPositionMatrix();
            vertex(vertexConsumer, pose, p, k, q, 0.4999F, ab);
            vertex(vertexConsumer, pose, p, 0.0F, q, 0.4999F, aa);
            vertex(vertexConsumer, pose, r, 0.0F, s, 0.0F, aa);
            vertex(vertexConsumer, pose, r, k, s, 0.0F, ab);
            vertex(vertexConsumer, pose, t, k, u, 0.4999F, ab);
            vertex(vertexConsumer, pose, t, 0.0F, u, 0.4999F, aa);
            vertex(vertexConsumer, pose, v, 0.0F, w, 0.0F, aa);
            vertex(vertexConsumer, pose, v, k, w, 0.0F, ab);
            poseStack.pop();
            super.render(hook, f, g, poseStack, multiBufferSource, i);

        }
    }

    private Vec3d getHandPos(PlayerEntity player, float f, float tickDelta) {
        int i = player.getMainArm() == Arm.RIGHT ? 1 : -1;
        ItemStack itemStack = player.getMainHandStack();
        if (!itemStack.isOf(Items.FISHING_ROD)) {
            i = -i;
        }

        if (this.dispatcher.gameOptions.getPerspective().isFirstPerson() && player
                == MinecraftClient.getInstance().player) {
            double m = 960.0 / (double)(Integer)this.dispatcher.gameOptions.getFov().getValue();
            Vec3d vec3d = this.dispatcher.camera.getProjection().getPosition((float)i * 0.525F,
                    -0.1F).multiply(m).rotateY(f * 0.5F).rotateX(-f * 0.7F);
            return player.getCameraPosVec(tickDelta).add(vec3d);
        } else {
            float g = MathHelper.lerp(tickDelta, player.prevBodyYaw, player.bodyYaw) * 0.017453292F;
            double d = (double)MathHelper.sin(g);
            double e = (double)MathHelper.cos(g);
            float h = player.getScale();
            double j = (double)i * 0.35 * (double)h;
            double k = 0.8 * (double)h;
            float l = player.isInSneakingPose() ? -0.1875F : 0.0F;
            return player.getCameraPosVec(tickDelta).add(-e * j - d * k,
                    (double)l - 0.45 * (double)h, -d * j + e * k);
        }
    }

    public Vec3d getPlayerHandPos(PlayerEntity player, float f, Item item, EntityRenderDispatcher entityRenderDispatcher) {
        int i = player.getMainArm() == Arm.RIGHT ? 1 : -1;
        ItemStack itemStack = player.getMainHandStack();
        if (!itemStack.isOf(item)) {
            i = -i;
        }

        float g = player.getAttackCooldownProgress(f);
        float h = MathHelper.sin(MathHelper.sqrt(g) * 3.1415927F);
        float j = MathHelper.lerp(f, player.prevBodyYaw, player.bodyYaw) * 0.017453292F;
        double d = MathHelper.sin(j);
        double e = MathHelper.cos(j);
        double k = (double)i * 0.35;
        double l = 0.8;
        if (this.dispatcher.gameOptions.getPerspective().isFirstPerson() && player == MinecraftClient.getInstance().player) {
            double n = 960.0 / (double)(Integer)this.dispatcher.gameOptions.getFov().getValue();
            Vec3d vec3 = entityRenderDispatcher.camera.getProjection().getPosition((float)i * 0.525F, -0.1F);
            vec3 = vec3.multiply(n);
            vec3 = vec3.rotateY(h * 0.5F);
            vec3 = vec3.rotateX(-h * 0.7F);
            return new Vec3d(MathHelper.lerp(f, player.lastRenderX, player.getX()) + vec3.x,
                    MathHelper.lerp(f, player.lastRenderY, player.getY()) + vec3.y +
                            (double)player.getStandingEyeHeight(), MathHelper.lerp((double)f, player.lastRenderY,
                    player.getZ()) + vec3.z);
        } else {
            float m = player.isInSneakingPose() ? -0.1875F : 0.0F;
            return new Vec3d(MathHelper.lerp(f, player.lastRenderX, player.getX()) - e * k - d
                    * 0.8, player.lastRenderY + (double)player.getStandingEyeHeight() + (player.getY() - player.lastRenderY)
                    * (double)f - 0.45 + (double)m, MathHelper.lerp((double)f, player.lastRenderZ, player.getZ()) - d * k + e * 0.8);
        }
    }


    private static float percentage(int value, int max) {
        return (float)value / (float)max;
    }

    private static void vertex(VertexConsumer vertexConsumer, MatrixStack.Entry pose, float f, float g, float h, float i, float j) {
        vertexConsumer.vertex(pose, f, g, h).color(-1).texture(i, j).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(0.0F, 1.0F, 0.0F);
    }

    private static void renderFishingLine(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd) {
        float f = x * segmentStart;
        float g = y * (segmentStart * segmentStart + segmentStart) * 0.5F + 0.25F;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + 0.25F - g;
        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;
        buffer.vertex(matrices, f, g, h).color(Colors.BLACK).normal(matrices, i, j, k);
    }
    @Override
    public Identifier getTexture(HookEntity entity) {
        return TEXTURE;
    }
}
