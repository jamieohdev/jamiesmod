package com.jamiedev.mod.client.renderer;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.client.JamiesModModelLayers;
import com.jamiedev.mod.client.models.JawsEntityModel;
import com.jamiedev.mod.client.models.SpitterModel;
import com.jamiedev.mod.entities.SpitterEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.SquidEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SpitterRenderer extends MobEntityRenderer<SpitterEntity, SpitterModel<SpitterEntity>> {
    private static final Identifier TEXTURE = JamiesMod.getModId("textures/entity/spitter.png");

    SquidEntityRenderer ref;

    public SpitterRenderer(EntityRendererFactory.Context context) {
        super(context, new SpitterModel(context.getPart(JamiesModModelLayers.SPITTER)), 0.5F);
    }

    public void render(SpitterEntity slimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        //this.shadowRadius = 0.25F * (float)slimeEntity.getSize();
        super.render(slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    protected void scale(SpitterEntity slimeEntity, MatrixStack matrixStack, float f) {
        float g = 0.999F;
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0F, 0.001F, 0.0F);

    }

    public Identifier getTexture(SpitterEntity slimeEntity) {
        return TEXTURE;
    }
}
