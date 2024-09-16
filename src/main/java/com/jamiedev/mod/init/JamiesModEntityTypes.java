package com.jamiedev.mod.init;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.entities.BrungleEntity;
import com.jamiedev.mod.entities.DuckEntity;
import com.jamiedev.mod.entities.GlareEntity;
import com.jamiedev.mod.entities.projectile.HookEntity;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class JamiesModEntityTypes {
    public static Identifier id(String name){
        return Identifier.of(JamiesMod.MOD_ID, name);
    }

    public static final EntityType<HookEntity> HOOK = Registry.register(Registries.ENTITY_TYPE,
            JamiesMod.getModId( "hook"),
            FabricEntityTypeBuilder.<HookEntity>create(SpawnGroup.MISC, HookEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(5)
                    .build()
    );

    public static final EntityType<DuckEntity> DUCK = Registry.register(Registries.ENTITY_TYPE,
            JamiesMod.getModId( "duck"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuckEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4F, 0.7F)).build());

    public static final EntityType<BrungleEntity> BRUNGLE = Registry.register(Registries.ENTITY_TYPE,
            JamiesMod.getModId( "brungle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BrungleEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5F, 1.0F)).build());

    public static final EntityType<GlareEntity> GLARE = Registry.register(Registries.ENTITY_TYPE,
            JamiesMod.getModId( "glare"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GlareEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 0.8F)).build());

    public static void init(){
        FabricDefaultAttributeRegistry.register(DUCK, DuckEntity.createDuckAttributes());
    }
}
