package com.jamiedev.mod.util;

import com.jamiedev.mod.entities.projectile.HookEntity;
import com.mojang.datafixers.types.templates.Hook;

import net.fabricmc.fabric.api.attachment.v1.*;

public interface PlayerWithHook {
    HookEntity getHook();
    void setHook(HookEntity hook);
}
