package com.jamiedev.mod.client;

import com.jamiedev.mod.init.JamiesModBlocks;
import com.jamiedev.mod.init.JamiesModDimension;
import com.jamiedev.mod.init.JamiesModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import com.jamiedev.mod.client.renderer.DuckieRenderer;
import com.jamiedev.mod.client.models.DuckieModel;

public class JamiesModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // JamiesModBlocks.customField.getAllBlocks().forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CLOUD, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_VINE, RenderLayer.getCutout());
        EntityRendererRegistry.register(JamiesModEntityTypes.DUCK, DuckieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JamiesModModelLayers.DUCKIE, DuckieModel::getTexturedModelData);

        //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, JamiesModSkyRenderer.INSTANCE);
        //DimensionRenderingRegistry.registerDimensionEffects(JamiesMod.getModId("special_effect"), JamiesModPlatform.INSTANCE.getDimEffect());
      //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, new LimboSkyRendererClient());
        //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, context -> JamiesModSkyRenderer.renderSky(context.world(), context.positionMatrix(),
        //       context.projectionMatrix(), context.tickCounter().getTickDelta(Minecraft.getInstance().level != null
        //               && Minecraft.getInstance().level.tickRateManager().runsNormally()), context.camera(), () -> {
        //}));

        //DimensionRenderingRegistry.registerCloudRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, new LimboCloudRendererClient());
    }
}
