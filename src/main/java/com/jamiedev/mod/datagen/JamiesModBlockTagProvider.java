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
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(JamiesModBlocks.DEAD_ORANGE_CORAL)
                .add(JamiesModBlocks.DEAD_ORANGE_CORAL_BLOCK)
                .add(JamiesModBlocks.DEAD_ORANGE_CORAL_FAN)
                .add(JamiesModBlocks.DEAD_ORANGE_CORAL_WALL_FAN)
                .add(JamiesModBlocks.ORANGE_CORAL)
                .add(JamiesModBlocks.ORANGE_CORAL_BLOCK)
                .add(JamiesModBlocks.ORANGE_CORAL_FAN)
                .add(JamiesModBlocks.ORANGE_CORAL_WALL_FAN)
                .add(JamiesModBlocks.DEAD_BLUE_CORAL)
                .add(JamiesModBlocks.DEAD_BLUE_CORAL_BLOCK)
                .add(JamiesModBlocks.DEAD_BLUE_CORAL_FAN)
                .add(JamiesModBlocks.DEAD_BLUE_CORAL_WALL_FAN)
                .add(JamiesModBlocks.BLUE_CORAL)
                .add(JamiesModBlocks.BLUE_CORAL_BLOCK)
                .add(JamiesModBlocks.BLUE_CORAL_FAN)
                .add(JamiesModBlocks.BLUE_CORAL_WALL_FAN);

        //        .add(JamiesModBlocks.PRIMORDIAL_VENT);
        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
                .add(JamiesModBlocks.ORANGE_CORAL_BLOCK);
        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
                .add(JamiesModBlocks.ORANGE_CORAL);
        getOrCreateTagBuilder(BlockTags.CORALS)
                .add(JamiesModBlocks.ORANGE_CORAL_FAN);
        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
                .add(JamiesModBlocks.ORANGE_CORAL_WALL_FAN);
        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
                .add(JamiesModBlocks.BLUE_CORAL_BLOCK);
        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
                .add(JamiesModBlocks.BLUE_CORAL);
        getOrCreateTagBuilder(BlockTags.CORALS)
                .add(JamiesModBlocks.BLUE_CORAL_FAN);
        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
                .add(JamiesModBlocks.BLUE_CORAL_WALL_FAN);
    }


}
