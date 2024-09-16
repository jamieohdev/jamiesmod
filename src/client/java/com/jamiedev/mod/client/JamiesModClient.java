package com.jamiedev.mod.client;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.client.particles.RafflesiaSporeParticle;
import com.jamiedev.mod.init.*;
import com.jamiedev.mod.util.PlayerWithHook;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import com.jamiedev.mod.client.renderer.*;
import com.jamiedev.mod.client.models.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class JamiesModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // JamiesModBlocks.customField.getAllBlocks().forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CLOUD, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_VINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.GOURD_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.RAFFLESIA, RenderLayer.getCutout());

        EntityRendererRegistry.register(JamiesModEntityTypes.DUCK, DuckieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JamiesModModelLayers.DUCKIE, DuckieModel::getTexturedModelData);

        EntityRendererRegistry.register(JamiesModEntityTypes.HOOK, HookRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JamiesModModelLayers.HOOK, DuckieModel::getTexturedModelData);

        ParticleFactoryRegistry.getInstance().register(JamiesModParticleTypes.RAFFLESIA_SPORES, RafflesiaSporeParticle.Factory::new);
        //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, JamiesModSkyRenderer.INSTANCE);
        //DimensionRenderingRegistry.registerDimensionEffects(JamiesMod.getModId("special_effect"), JamiesModPlatform.INSTANCE.getDimEffect());
      //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, new LimboSkyRendererClient());
        //DimensionRenderingRegistry.registerSkyRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, context -> JamiesModSkyRenderer.renderSky(context.world(), context.positionMatrix(),
        //       context.projectionMatrix(), context.tickCounter().getTickDelta(Minecraft.getInstance().level != null
        //               && Minecraft.getInstance().level.tickRateManager().runsNormally()), context.camera(), () -> {
        //}));

        //DimensionRenderingRegistry.registerCloudRenderer(JamiesModDimension.LIMBO_LEVEL_KEY, new LimboCloudRendererClient());

        registerModelPredicateProviders();
    }
    public static void registerModelPredicateProviders() {
        // For versions before 1.21, replace 'Identifier.ofVanilla' with 'new Identifier'.
        ModelPredicateProviderRegistry.register(JamiesModItems.HOOK, JamiesMod.getModId("deployed"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity instanceof PlayerEntity) {
                for (Hand value : Hand.values())
                {
                    ItemStack heldStack = livingEntity.getStackInHand(value);
                    if (heldStack == itemStack && (((PlayerWithHook)livingEntity).getHook()  != null && !((PlayerWithHook)livingEntity).getHook().isRemoved()))
                    {
                        return 1;
                    }
                }
            }
            if (livingEntity == null)
            {
                return 0.0F;
            }
            return 0;
        });
    }


}
