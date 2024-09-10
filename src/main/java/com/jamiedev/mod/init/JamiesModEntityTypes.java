package com.jamiedev.mod.init;
import com.jamiedev.mod.JamiesMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JamiesModEntityTypes {

    public static Identifier jamiesMobID() {
        return id("jamiesmob");
    }

    public static Identifier id(String name){
        return Identifier.of(JamiesMod.MOD_ID, name);
    }
    public static final EntityType<CowEntity> GIPPLE = Registry.register(
            Registries.ENTITY_TYPE,
            jamiesMobID(),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CowEntity::new).dimensions(EntityDimensions.changing(0.75f, 0.5f).withEyeHeight(0.5f * 0.8f)).build()
    );

    public static void init(){
        FabricDefaultAttributeRegistry.register(GIPPLE, CowEntity.createCowAttributes());
    }
}
