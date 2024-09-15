package com.jamiedev.mod.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;

public class GlareEntity extends AnimalEntity implements Flutterer
{
    /*
    Glare will be pretty different, has different sizes that the smaller ones gather to.
     */
    private static final TrackedData<Integer> GLARE_SIZE;
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 127;
    private Vec2f targetEyesPositionOffset;
    private static final float MOVEMENT_SPEED = 0.1F;
    public static final int MIN_EYE_ANIMATION_TICK_AMOUNT = 10;


    public GlareEntity(EntityType<? extends GlareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new GlaresMoveControl(this, 16, true);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.LAVA, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
        this.setCanPickUpLoot(true);

        this.targetEyesPositionOffset = new Vec2f(0.0F, 0.0F);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(GLARE_SIZE, 1);
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (GLARE_SIZE.equals(data)) {
            this.calculateDimensions();
            this.setYaw(this.headYaw);
            this.bodyYaw = this.headYaw;
            if (this.isTouchingWater() && this.random.nextInt(20) == 0) {
                //this.onSwimmingStart();
            }
        }

        super.onTrackedDataSet(data);
    }


    @Override
    public void tickMovement() {
        super.tickMovement();

        if (!this.getWorld().isClient() && this.isAlive() && this.age % 10 == 0) {
            this.heal(1.0F);
        }
    }

    public Vec2f getTargetEyesPositionOffset() {
        return this.targetEyesPositionOffset;
    }

    public void setTargetEyesPositionOffset(float xEyePositionOffset, float yEyePositionOffset) {
        this.targetEyesPositionOffset = new Vec2f(xEyePositionOffset, yEyePositionOffset);
    }

    private void updateTargetEyesPositionOffset() {
        if (
                this.age % MIN_EYE_ANIMATION_TICK_AMOUNT != 0
                        || this.getRandom().nextBetween(0, 2) != 0
        ) {
            return;
        }

        this.setTargetEyesPositionOffset(
                -0.5F + this.getRandom().nextFloat(),
                -0.4F + this.getRandom().nextFloat() * (0.4F - -0.4F)
        );
    }

    public static Builder createGlareAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    @Override
    public void tick() {

        super.tick();
        this.updateTargetEyesPositionOffset();
    }

    @Override
    protected void mobTick() {
        this.getWorld().getProfiler().push("glareBrain");
        //this.getBrain().tick((ServerWorld) this.getWorld(), this);
        this.getWorld().getProfiler().pop();
        this.getWorld().getProfiler().push("glareMemoryUpdate");
        //GlareBrain.updateMemories(this);
        this.getWorld().getProfiler().pop();
        this.getWorld().getProfiler().push("glareActivityUpdate");
        //GlareBrain.updateActivities(this);
        this.getWorld().getProfiler().pop();

        super.mobTick();
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.800000011920929));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.9100000262260437));
            }
        }

        this.updateLimbs(false);
    }

    public void stopMovement() {
        this.getBrain().forget(MemoryModuleType.AVOID_TARGET);
        this.getBrain().forget(MemoryModuleType.WALK_TARGET);
        this.getBrain().forget(MemoryModuleType.LOOK_TARGET);

        this.getNavigation().setSpeed(0);
        this.getNavigation().stop();
        this.getMoveControl().moveTo(this.getX(), this.getY(), this.getZ(), 0);
        this.getMoveControl().tick();
        this.getLookControl().lookAt(this.getLookControl().getLookX(), this.getLookControl().getLookY(), this.getLookControl().getLookZ());
        this.getLookControl().lookAt(Vec3d.ZERO);
        this.getLookControl().tick();

        this.setJumping(false);
        this.setMovementSpeed(0.0F);
        this.prevHorizontalSpeed = 0.0F;
        this.horizontalSpeed = 0.0F;
        this.sidewaysSpeed = 0.0F;
        this.upwardSpeed = 0.0F;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public boolean isInAir() {
        return !this.isOnGround();
    }

    protected void swimUpward(TagKey<Fluid> tagKey) {
        this.setVelocity(this.getVelocity().add(0.0, 0.01, 0.0));
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0D, this.getStandingEyeHeight() * 0.6D, 0.0D);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world)
        {
            public boolean isValidPosition(BlockPos pos) {
                boolean isValidPos = this.world.getBlockState(pos.down()).isAir() == false && this.world.getBlockState(pos.down()).isLiquid() == false;

                return isValidPos;
            }
        };

        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);

        return birdNavigation;
    }

    final class GlaresMoveControl extends FlightMoveControl
    {
        public GlaresMoveControl(GlareEntity glare, int maxPitchChange, boolean noGravity) {
            super(glare, maxPitchChange, noGravity);
        }

        @Override
        public void tick() {
            if (GlareEntity.this.isAttacking()) {
                return;
            }

            super.tick();
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    static {
        GLARE_SIZE = DataTracker.registerData(GlareEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

}
