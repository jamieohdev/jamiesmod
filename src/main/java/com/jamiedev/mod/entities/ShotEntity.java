package com.jamiedev.mod.entities;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.init.JamiesModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class ShotEntity extends ProjectileEntity
{
    private static TrackedData<Float> LENGTH;
    private static TrackedData<Boolean> IN_BLOCK;

    public ShotEntity(EntityType<? extends ShotEntity> entityType, World world) {
        super(entityType, world);
        PlayerEntity player = this.getPlayerOwner();
        this.ignoreCameraFrustum = true;
        this.setOwner(player);
        this.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        // this.setVelocity(player.getViewVector(1.0F).scale(3.0));

        this.setVelocity(player.getRotationVec(1.0F).multiply(3.0));
    }



    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(LENGTH, 0.0F);
        builder.add(IN_BLOCK, false);
    }

    public boolean shouldRender(double distance) {
        return true;
    }

    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
    }
    public void tick() {
        super.tick();
        PlayerEntity player = this.getPlayerOwner();
        if (player != null && (this.getWorld().isClient || !this.removeIfInvalid(player))) {
            HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onCollision(hitResult);
            }

            this.setPosition(hitResult.getPos());
            this.checkBlockCollision();
        } else {
            this.discard();
        }
    }


    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(Items.FISHING_ROD);
        boolean bl2 = itemStack2.isOf(Items.FISHING_ROD);
        if (!player.isRemoved() && player.isAlive() && (bl || bl2) && !(this.squaredDistanceTo(player) > 10000.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }
    private void updateHookedEntityId(@Nullable Entity entity) {

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            this.updateHookedEntityId(entityHitResult.getEntity());
        }

    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.setVelocity(Vec3d.ZERO);
        this.setInBlock(true);
        PlayerEntity player = (PlayerEntity) this.getOwner();
        if (player != null)
        {
            double d = player.getEyePos().subtract(blockHitResult.getPos()).length();
            this.setLength(Math.max((float)d * 0.5F - 3.0F, 1.5F));
        }
    }
    private void setInBlock(boolean bl) {
        this.dataTracker.set(IN_BLOCK, bl);
    }

    private void setLength(float f) {
        this.dataTracker.set(LENGTH, f);
    }

    public boolean inBlock() {
        return this.dataTracker.get(IN_BLOCK);
    }

    public float length() {
        return this.dataTracker.get(LENGTH);
    }

    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public void remove(Entity.RemovalReason removalReason) {
        this.updateOwnerInfo(null);
        super.remove(removalReason);
    }

    public void onClientRemoval() {
        this.updateOwnerInfo(null);
    }

    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable ShotEntity webShot) {
       PlayerEntity player = this.getPlayerOwner();
        if (player != null) {
           // player.setData(JamiesModAttachmentTypes.GRAPPLING, new EntityHolder<>(webShot));

        }

    }

    @Nullable
    public PlayerEntity getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof PlayerEntity ? (PlayerEntity)entity : null;
    }

    @Override
    public boolean canTeleportBetween(World from, World to) {
        return false;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
        Entity entity = this.getOwner();
        return new EntitySpawnS2CPacket(this, entityTrackerEntry, entity == null ? this.getId() : entity.getId());
    }

    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (this.getPlayerOwner() == null) {
            int i = packet.getEntityData();
            JamiesMod.LOGGER.error("Failed to recreate SHOT on client. {} (id: {}) is not a valid owner.", this.getWorld().getEntityById(i), i);
            this.kill();
        }

    }
}
