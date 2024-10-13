package com.jamiedev.mod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.FollowGroupLeaderGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.security.Guard;

public class CoelacanthEntity extends SchoolingFishEntity
{
    SkeletonEntity ref;

    public CoelacanthEntity(EntityType<? extends CoelacanthEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new FleeEntityGoal(this, PlayerEntity.class, 6.0F, 1.0, 1.2));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_SALMON_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return Items.WATER_BUCKET.getDefaultStack();
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.getWorld().isClient) {
            return false;
        } else {
            if (!source.isIn(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !source.isOf(DamageTypes.THORNS)) {
                Entity var4 = source.getSource();
                if (var4 instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity)var4;
                    livingEntity.damage(this.getDamageSources().thorns(this), 0.1F);
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0), this);
                    this.playSound(SoundEvents.BLOCK_CALCITE_HIT, 1.0F, 1.0F);
                }
            }


            return super.damage(source, amount);
        }
    }
}
