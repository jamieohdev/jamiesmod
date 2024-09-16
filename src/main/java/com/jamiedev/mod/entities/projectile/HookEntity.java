package com.jamiedev.mod.entities.projectile;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.init.JamiesModAttatchments;
import com.jamiedev.mod.init.JamiesModEntityTypes;
import com.jamiedev.mod.init.JamiesModItems;
import com.jamiedev.mod.util.EntityHolder;
import com.jamiedev.mod.util.PlayerWithHook;
import com.mojang.datafixers.types.templates.Hook;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HookEntity extends ProjectileEntity
{
    public static final TrackedData<Boolean> IN_BLOCK = DataTracker.registerData(HookEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Float> LENGTH = DataTracker.registerData(HookEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public HookEntity(EntityType<? extends HookEntity> entityType, World pLevel) {
        super(JamiesModEntityTypes.HOOK, pLevel);
        this.ignoreCameraFrustum = true;
    }

    public HookEntity(World level, PlayerEntity player) {
        super(JamiesModEntityTypes.HOOK, level);
        setOwner(player);
        setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        setVelocity(player.getLeashPos(1.0F).multiply(3.0));
    }



    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(LENGTH, 0.0F);
        builder.add(IN_BLOCK, false);
    }

    public void writeCustomDataToNbt(NbtCompound compoundTag) {
        compoundTag.putBoolean("in_block", this.inBlock());
        compoundTag.putFloat("length", this.length());
    }

    public void readCustomDataFromNbt(NbtCompound compoundTag) {
        this.setInBlock(compoundTag.getBoolean("in_block"));
        this.setLength(compoundTag.getFloat("length"));
    }

    public boolean shouldRender(double distance) {
        return true;
    }

    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
    }

    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public void remove(Entity.RemovalReason removalReason) {
        this.updateOwnerInfo(null);
        super.remove(removalReason);
    }

    public void onRemoved() {
        this.updateOwnerInfo(null);
    }

    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable HookEntity hook) {
        PlayerEntity player = this.getPlayerOwner();
        if (player != null) {
            ((PlayerWithHook)player).setHook(hook);
            player.getAttachedOrCreate(JamiesModAttatchments.GRAPPLING);
        }

    }

    @Nullable
    public PlayerEntity getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof PlayerEntity ? (PlayerEntity)entity : null;
    }

    public boolean shouldRetract(PlayerEntity player)
    {
        return player.isRemoved() || !player.isAlive() || !player.isHolding(JamiesModItems.HOOK) || this.squaredDistanceTo(player) > 10000.0;
    }

    public void tick() {
        super.tick();
        PlayerEntity player = this.getPlayerOwner();

        if (player != null && (this.getWorld().isClient || !this.shouldRetract(player)))
        {
            HitResult hit = ProjectileUtil.getCollision(this, this::canHit);
            if (hit.getType() != HitResult.Type.MISS)
            {
                this.onEntityHit((EntityHitResult) hit);
            }
            setPosition(hit.getPos());
            this.checkBlockCollision();
        }
        else
        {
            this.discard();
        }
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.setPosition(Vec3d.ZERO);
        this.setInBlock(true); // idk
        PlayerEntity player = this.getPlayerOwner();
        if (player != null) {
            double d = player.getEyePos().subtract(blockHitResult.getPos()).length();
            this.setLength(Math.max((float)d * 0.5F - 3.0F, 1.5F));
        }

    }

    private void setInBlock(boolean bl) {
        this.getDataTracker().set(IN_BLOCK, bl);
    }

    private void setLength(float f) {
        this.getDataTracker().set(LENGTH, f);
    }

    public boolean inBlock() {
        return this.getDataTracker().get(IN_BLOCK);
    }

    public float length() {
        return this.getDataTracker().get(LENGTH);
    }

    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
        Entity entity = this.getOwner();
        return new EntitySpawnS2CPacket(this, entityTrackerEntry, entity == null ? this.getId() : entity.getId());
    }

    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (this.getPlayerOwner() == null) {
            int i = packet.getEntityData();
            JamiesMod.LOGGER.error("Failed to recreate grappling hook on client. {} (id: {}) is not a valid owner.", this.getWorld().getEntityById(i), i);
            this.kill();
        }

    }
}
