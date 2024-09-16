package com.jamiedev.mod.entities.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;

public abstract class AbstractChainEntity extends ProjectileEntity {
    public float lastDelta = 0.0F;
    ArrowEntity arrow;
    protected AbstractChainEntity(EntityType<? extends ProjectileEntity> pEntityType, World pLevel){
        super(pEntityType, pLevel);
    }
}