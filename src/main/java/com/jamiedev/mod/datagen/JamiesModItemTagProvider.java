package com.jamiedev.mod.datagen;

import com.jamiedev.mod.init.JamiesModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
public class JamiesModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public JamiesModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(JamiesModTags.Items.ANCIENT_LOGS)
                .add(JamiesModBlocks.ANCIENT_LOG.asItem())
                .add(JamiesModBlocks.ANCIENT_WOOD.asItem())
                .add(JamiesModBlocks.STRIPPED_ANCIENT_LOG.asItem())
                .add(JamiesModBlocks.STRIPPED_ANCIENT_WOOD.asItem());
   }
}
