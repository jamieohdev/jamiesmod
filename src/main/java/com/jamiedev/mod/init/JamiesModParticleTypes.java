package com.jamiedev.mod.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.jamiedev.mod.JamiesMod;
public class JamiesModParticleTypes
{
    public static final ParticleType<SimpleParticleType> RAFFLESIA_SPORES = FabricParticleTypes.simple();
    public static final ParticleType<SimpleParticleType> ALGAE_BLOOM = FabricParticleTypes.simple();

    public static final ParticleType<SimpleParticleType> BLEMISH = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registries.PARTICLE_TYPE, JamiesMod.getModId( "rafflesia_spores"), RAFFLESIA_SPORES);
        Registry.register(Registries.PARTICLE_TYPE, JamiesMod.getModId( "algae_bloom"), ALGAE_BLOOM);
        Registry.register(Registries.PARTICLE_TYPE, JamiesMod.getModId( "blemish_bubble"), BLEMISH);
    }
}
