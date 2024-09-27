package com.jamiedev.mod.worldgen.feature.config;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
public record SmallCloudConfig(int bounds, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<SmallCloudConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("bounds").forGetter(SmallCloudConfig::bounds),
            BlockStateProvider.TYPE_CODEC.fieldOf("blocks").forGetter(SmallCloudConfig::block)
    ).apply(instance, SmallCloudConfig::new));
}