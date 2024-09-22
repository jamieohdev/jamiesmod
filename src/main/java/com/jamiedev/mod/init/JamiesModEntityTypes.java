package com.jamiedev.mod.init;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.entities.BigBeakEntity;
import com.jamiedev.mod.entities.BrungleEntity;
import com.jamiedev.mod.entities.DuckEntity;
import com.jamiedev.mod.entities.GlareEntity;
import com.jamiedev.mod.entities.projectile.HookEntity;
import com.jamiedev.mod.mixin.SpawnRestrictMixin;
import net.minecraft.entity.*;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class JamiesModEntityTypes {

    public class CustomSpawnGroups
    {
        public static SpawnGroup GLARES, BIG_BEAKS;

        public CustomSpawnGroups()
        {

        }

        public static  SpawnGroup getGlares() {
            return GLARES;
        }

        public static SpawnGroup getBigBeaks() {
            return BIG_BEAKS;
        }
    }

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

    public static final EntityType<BigBeakEntity> BIG_BEAK = Registry.register(Registries.ENTITY_TYPE,
            JamiesMod.getModId( "big_beak"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BigBeakEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0F, 2.0F)).build());

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

    public static void init()
    {
        SpawnRestriction.register(GLARE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlareEntity::canSpawn);
        SpawnRestrictMixin.callRegister(BIG_BEAK, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BigBeakEntity::canSpawn);
        FabricDefaultAttributeRegistry.register(DUCK, DuckEntity.createDuckAttributes());
        FabricDefaultAttributeRegistry.register(BIG_BEAK, BigBeakEntity.createBigBeakAttributes());
        FabricDefaultAttributeRegistry.register(GLARE, GlareEntity.createGlareAttributes());

    }

    public static void postInit() {
        initSpawnRestrictions();
    }

    public static void initSpawnRestrictions() {
        SpawnRestrictMixin.callRegister(GLARE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlareEntity::canSpawn);
        SpawnRestrictMixin.callRegister(BIG_BEAK, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BigBeakEntity::canSpawn);
        SpawnRestriction.register(GLARE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, GlareEntity::canSpawn);
        SpawnRestriction.register(BIG_BEAK, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, BigBeakEntity::canSpawn);
   }
}
