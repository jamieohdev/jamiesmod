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
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(JamiesModBlocks.LIMBOSLATE)
                        .add(JamiesModBlocks.LIMBOSTONE);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(JamiesModBlocks.ANCIENT_LEAVES)
                .add(JamiesModBlocks.ANCIENT_ROOTS)
                .add(JamiesModBlocks.CLOUD);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(JamiesModBlocks.LIMBOSTONE)
                        .add(JamiesModBlocks.LIMBOSLATE);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(JamiesModBlocks.ANCIENT_LOG)
                .add(JamiesModBlocks.ANCIENT_WOOD)
                .add(JamiesModBlocks.STRIPPED_ANCIENT_LOG)
                .add(JamiesModBlocks.STRIPPED_ANCIENT_WOOD)
                .add(JamiesModBlocks.ANCIENT_PLANKS)
                .add(JamiesModBlocks.ANCIENT_STAIRS)
                .add(JamiesModBlocks.ANCIENT_SLAB)
                .add(JamiesModBlocks.ANCIENT_FENCE)
                .add(JamiesModBlocks.ANCIENT_FENCE_GATE)
                .add(JamiesModBlocks.ANCIENT_DOOR)
                .add(JamiesModBlocks.ANCIENT_TRAPDOOR)
                .add(JamiesModBlocks.ANCIENT_PRESSURE_PLATE)
                .add(JamiesModBlocks.ANCIENT_BUTTON);
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
