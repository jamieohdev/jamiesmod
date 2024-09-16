package com.jamiedev.mod.mixin;

import com.jamiedev.mod.entities.projectile.HookEntity;
import com.jamiedev.mod.util.PlayerWithHook;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import com.jamiedev.mod.entities.*;
@Mixin(PlayerEntity.class)
public abstract class PlayerMixin implements PlayerWithHook {
    HookEntity data;

    @Override
    public HookEntity getHook() {
        return this.data;
    }

    @Override
    public void setHook(HookEntity value) {
        this.data = value;
    }


}
