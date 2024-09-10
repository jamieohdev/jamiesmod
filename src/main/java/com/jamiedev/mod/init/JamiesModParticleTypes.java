package com.jamiedev.mod.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.jamiedev.mod.JamiesMod;
public class JamiesModParticleTypes
{
    public static final ParticleType EXAMPLE_PARTICLE = FabricParticleTypes.simple();

    public static void registerModParticles() {
        Registry.register(Registries.PARTICLE_TYPE, JamiesMod.getModId( "example_particle"), EXAMPLE_PARTICLE);
    }
}
