package com.jamiedev.mod.client;

import com.jamiedev.mod.init.JamiesModBlocks;
import com.jamiedev.mod.init.JamiesModDimension;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.RenderLayer;

public class JamiesModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // JamiesModBlocks.customField.getAllBlocks().forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CLOUD, RenderLayer.getTranslucent());

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
