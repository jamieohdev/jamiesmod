package com.jamiedev.mod.datagen;
import com.jamiedev.mod.init.JamiesModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.concurrent.CompletableFuture;

public class JamiesModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public JamiesModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(JamiesModBlocks.ANCIENT_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(JamiesModBlocks.ANCIENT_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(JamiesModBlocks.ANCIENT_HANGING_SIGN);


        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(JamiesModBlocks.ANCIENT_WALL_HANGING_SIGN);
    }


}
