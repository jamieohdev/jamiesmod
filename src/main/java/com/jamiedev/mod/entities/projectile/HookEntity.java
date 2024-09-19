package com.jamiedev.mod.entities.projectile;

import com.jamiedev.mod.init.JamiesModEntityTypes;
import com.jamiedev.mod.init.JamiesModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HookEntity extends PersistentProjectileEntity
{

    public HookEntity(EntityType<? extends HookEntity> entityType, World pLevel) {
        super(entityType, pLevel);
        this.ignoreCameraFrustum = true;
    }

    public HookEntity(World level, PlayerEntity player) {
        super(JamiesModEntityTypes.HOOK, level);
        setOwner(player);
        setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return JamiesModItems.HOOK.getDefaultStack();
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    @Nullable
    public PlayerEntity getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof PlayerEntity ? (PlayerEntity)entity : null;
    }

    @Override
    public void tick() {
        super.tick();
        PlayerEntity player = this.getPlayerOwner();
        if((player == null || this.shouldRetract(player)) && !this.getWorld().isClient) {
            this.discard();
        }
    }

    private boolean shouldRetract(PlayerEntity player) {
        return player.isRemoved() || !player.isAlive() || !player.isHolding(JamiesModItems.HOOK) || this.squaredDistanceTo(player) > 10000.0;
    }

    @Override
    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }
}
