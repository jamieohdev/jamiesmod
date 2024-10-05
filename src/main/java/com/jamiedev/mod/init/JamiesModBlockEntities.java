package com.jamiedev.mod.init;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.blocks.entity.PrimordialUrchinEntity;
import com.jamiedev.mod.blocks.entity.PrimordialVentEntity;
import com.jamiedev.mod.worldgen.structure.AncientRootStructure;
import com.jamiedev.mod.worldgen.structure.TestRootStructure;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Locale;

public class JamiesModBlockEntities <T extends BlockEntity>
{
    BlockEntityType ref;

    public static BlockEntityType<PrimordialVentEntity> PRIMORDIAL_VENT;

    public static BlockEntityType<PrimordialUrchinEntity> PRIMORDIAL_URCHIN;

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, JamiesMod.getModId(name), type);
    }

    public static void init()
    {
        PRIMORDIAL_VENT = register("primordial_vent",
                BlockEntityType.Builder.create(PrimordialVentEntity::new, JamiesModBlocks.PRIMORDIAL_VENT)
                        .build());
        PRIMORDIAL_URCHIN = register("primordial_urchin",
                BlockEntityType.Builder.create(PrimordialUrchinEntity::new, JamiesModBlocks.PRIMORDIAL_URCHIN)
                        .build());
    }
}
