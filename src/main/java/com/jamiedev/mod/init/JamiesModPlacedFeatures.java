package com.jamiedev.mod.init;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;
import com.jamiedev.mod.*;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.treeModifiers;
public class JamiesModPlacedFeatures
{
    public static List<RegistryKey<PlacedFeature>> features = new ArrayList<>();

    public static RegistryKey<PlacedFeature> of(String id){
        RegistryKey<PlacedFeature> registryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, JamiesMod.getModId(id));
        features.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<PlacedFeature> ANCIENT_TREE_CHECKED = of("ancient_tree_checked");

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> holderGetter = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> fir = holderGetter.getOrThrow(JamiesModConfiguredFeatures.ANCIENT_TREE);
        PlacedFeatures.register(featureRegisterable, ANCIENT_TREE_CHECKED, fir, PlacedFeatures.wouldSurvive(JamiesModBlocks.ANCIENT_SAPLING));
  }


    public static void init() {

//        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, AZALEA_BUSH);
    }
}
