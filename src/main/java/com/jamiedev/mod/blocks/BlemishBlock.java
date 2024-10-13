package com.jamiedev.mod.blocks;

import com.jamiedev.mod.init.JamiesModBlocks;
import com.jamiedev.mod.init.JamiesModParticleTypes;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BlemishBlock extends Block
{
    CactusBlock ref;
    public BlemishBlock(Settings settings) {
        super(settings);
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Vec3d vec3d = new Vec3d(0.25, 0.05000000074505806, 0.25);
        entity.slowMovement(state, vec3d);
        entity.damage(world.getDamageSources().wither(), 2.0F);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!((double)fallDistance < 4.0) && entity instanceof LivingEntity livingEntity) {
            LivingEntity.FallSounds fallSounds = livingEntity.getFallSounds();
            SoundEvent soundEvent = (double)fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
            entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    public static boolean canWalkOnBlemish(Entity entity) {
        if (entity.getType().isIn(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
            return true;
        } else {
            return entity instanceof LivingEntity && !((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isEmpty();
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

            if (random.nextInt(100) == 0) {
                //world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            double d = (double)pos.getX() + 0.5 + (0.5 - random.nextDouble());
            double e = (double)pos.getY() + 1.0;
            double f = (double)pos.getZ() + 0.5 + (0.5 - random.nextDouble());
            double g = (double)random.nextFloat() * 0.04;
            world.addParticle((ParticleEffect)JamiesModParticleTypes.BLEMISH, d, e, f, 0.0, g, 0.0);

    }
}
