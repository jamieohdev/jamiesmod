package com.jamiedev.mod.init;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.worldgen.structure.AncientRootGenerator;
import com.jamiedev.mod.worldgen.structure.AncientRootStructure;
import com.jamiedev.mod.worldgen.structure.TestRootStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class JamiesModStructures
{
    public static StructureType<AncientRootStructure> ANCIENT_ROOTS;
    public static StructureType<TestRootStructure> TEST_ROOTS;

    public static void init()
    {
        ANCIENT_ROOTS = Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(JamiesMod.MOD_ID, "ancient_roots"), () -> AncientRootStructure.CODEC);
        TEST_ROOTS = Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(JamiesMod.MOD_ID, "test_roots"), () -> TestRootStructure.CODEC);
    }
}
