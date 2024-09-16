package com.jamiedev.mod.util;

import net.minecraft.entity.Entity;

public class EntityHolder<T extends Entity> {
    public T entity;

    public EntityHolder(T entity) {
        this.entity = entity;
    }
}