package com.jamiedev.mod.init;
import com.jamiedev.mod.JamiesMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
public class JamiesModTag
{
    public static final TagKey<Block> BIG_BEAK_SPAWNABLE_ON = create("big_beak_spawnable_on");
    public static final TagKey<Block> GLARE_SPAWNABLE_ON = create("glare_spawnable_on");

    private static TagKey<Block> create(String name) {
        return TagKey.of(RegistryKeys.BLOCK, JamiesMod.getModId(name));
    }
}
