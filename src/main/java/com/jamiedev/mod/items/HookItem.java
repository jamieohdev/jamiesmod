package com.jamiedev.mod.items;

import com.jamiedev.mod.entities.projectile.HookEntity;
import com.jamiedev.mod.util.PlayerWithHook;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class HookItem extends Item
{
    public HookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PlayerWithHook hookuser = (PlayerWithHook)user;
        ItemStack itemStack = user.getStackInHand(hand);
        HookEntity hook = hookuser.getHook();

        if (hook != null) {
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            retrieve(world,user, hook);
        } else {
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (world instanceof ServerWorld) {
                itemStack.damage(1, user, LivingEntity.getSlotForHand(hand));
            }

            this.shoot(world, user);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    FishingRodItem ref;
    TridentItem ref2;

    private void shoot(World world, PlayerEntity user) {
        if (!world.isClient)
        {
            world.spawnEntity(new HookEntity(world, user));
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
    }

    protected void shoot(LivingEntity Hook, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(Hook, Hook.getPitch(), Hook.getYaw() + yaw, 0.0F, speed, divergence);
    }

    private static void retrieve(World level, PlayerEntity player, HookEntity lashingPotatoHook) {
        if (!level.isClient()) {
            lashingPotatoHook.discard();
        }

        player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
    }
}
