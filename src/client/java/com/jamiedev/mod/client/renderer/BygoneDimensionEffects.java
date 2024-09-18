package com.jamiedev.mod.client.renderer;

import com.jamiedev.mod.init.JamiesModBiomes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

public class BygoneDimensionEffects extends DimensionEffects
{

    public static final BygoneDimensionEffects INSTANCE = new BygoneDimensionEffects(Float.NaN, false, SkyType.END, false, true);
    private final MinecraftClient minecraft = MinecraftClient.getInstance();
    private int rainSoundTime;

    public BygoneDimensionEffects(float cloudsHeight, boolean alternateSkyColor, SkyType skyType, boolean brightenLighting, boolean darkened) {
        super(cloudsHeight, alternateSkyColor, skyType, brightenLighting, darkened);
    }

    @Override
    public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
        return null;
    }

    @Override
    public boolean useThickFog(int camX, int camY) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        //if (player != null) {
        //
       // }

        return true;
    }
}
