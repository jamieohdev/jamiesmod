package com.jamiedev.mod.util;

import com.jamiedev.mod.entities.projectile.HookEntity;
import org.jetbrains.annotations.Nullable;

public interface PlayerWithHook {
    @Nullable HookEntity jamiesmod$getHook();
    void jamiesmod$setHook(@Nullable HookEntity pHook);
}
