package com.jamiedev.mod.items;

import com.jamiedev.mod.entities.ShotEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ShooterItem extends Item
{
    public ShooterItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        return null;
    }

    public TypedActionResult<ItemStack> use1(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.fishHook != null) {
            if (!world.isClient) {
                int i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, LivingEntity.getSlotForHand(hand));
            }

            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)world;
                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, itemStack, user) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverWorld, itemStack, user);
                //sworld.spawnEntity(new ShotEntity(user.fishHook, world));
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
    }

    private static void retrieve(World level, PlayerEntity player, ShotEntity lashingPotatoHook) {
        if (!level.isClient()) {
            lashingPotatoHook.discard();
            //player.setData(MarvelAttachmentTypes.GRAPPLING, new EntityHolder<>(null));
        }

        player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
    }
}
