package com.jamiedev.mod.entities;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.EnumSet;

public class SpitterEntity  extends WaterCreatureEntity implements RangedAttackMob
{

    SpitterEntity ref;
    int attackCooldown = 0;
    int attackAnimTick;

    public SpitterEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new SpitterMoveControl(this);
        //this.lookControl = new LookControl(this);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new MoveIntoWaterGoal(this));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 0.8F));
        this.goalSelector.add(2, new ProjectileAttackGoal(this, 0.5D, 20, 10.0F));
        this.goalSelector.add(3, new SwimAroundGoal(this, 1.0D, 1)
        );
        //this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SquidEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, AnimalEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));

        //this.goalSelector.add(2, new )
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        //this.lookAt(target, 100);
        this.bodyYaw = prevBodyYaw;
        TridentEntity glass = new TridentEntity(this.getWorld(), this, new ItemStack(Items.TRIDENT));
        double xDistance = target.getX() - this.getX();
        double yDistance = target.getBodyY(0.3333333333333333D) - glass.getY();
        double zDistance = target.getZ() - this.getZ();
        double yMath = Math.sqrt((float) ((xDistance * xDistance) + (zDistance * zDistance)));
        glass.setVelocity(xDistance, yDistance + yMath * 0.10000000298023224D, zDistance, 1.6F, 11.0F);
        this.playSound(SoundEvents.ENTITY_BREEZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(glass);
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.attackAnimTick = 10;
        this.getWorld().sendEntityStatus(this, (byte)4);
        //boolean bl = super.tryAttack(target);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = target.damage(getDamageSources().mobAttackNoAggro(this), f1);

        if (flag)
        {
            World var7 = this.getWorld();
            ServerWorld serverWorld2 = (ServerWorld)var7;
            target.setVelocity(target.getVelocity().add(0.0D, (double)0.4F, 0.0D));
            DamageSource damageSource = this.getDamageSources().mobAttack(this);
            EnchantmentHelper.onTargetDamaged(serverWorld2, target, damageSource);

        }

        return flag;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public int getAttackAnimationTick() {
        return this.attackAnimTick;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackAnimTick = 10;
        }
        super.handleStatus(status);

    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    public void tick()
    {
        super.tick();

        if (this.attackCooldown > 0)
        {
            this.attackCooldown--;
        }

    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }

    }

    public void tickMovement() {
        if (!this.isTouchingWater() && this.isOnGround() && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4000000059604645, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.velocityDirty = true;
           // this.playSound(this.getFlopSound());
        }

        super.tickMovement();
    }


    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> entityType, WorldAccess iServerWorld, SpawnReason reason, BlockPos pos, Random random) {
        return iServerWorld.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)
                && iServerWorld.getBlockState(pos.up()).isOf(Blocks.WATER)
                && isLightLevelOk(pos, iServerWorld);
    }

    private static boolean isLightLevelOk(BlockPos pos, WorldAccess iServerWorld) {
        int light = iServerWorld.getLightLevel(pos);
        return light <= 4;
    }

    static class LookAtTargetGoal extends Goal {
        private final SpitterEntity Spitter;

        public LookAtTargetGoal(SpitterEntity Spitter) {
            this.Spitter = Spitter;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            return true;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            if (this.Spitter.getTarget() == null) {
                Vec3d vec3d = this.Spitter.getVelocity();
                this.Spitter.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776F);
                this.Spitter.bodyYaw = this.Spitter.getYaw();
            } else {
                LivingEntity livingEntity = this.Spitter.getTarget();
                double d = 64.0;
                if (livingEntity.squaredDistanceTo(this.Spitter) < 4096.0) {
                    double e = livingEntity.getX() - this.Spitter.getX();
                    double f = livingEntity.getZ() - this.Spitter.getZ();
                    this.Spitter.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776F);
                    this.Spitter.bodyYaw = this.Spitter.getYaw();
                }
            }

        }
    }

    protected boolean hasSelfControl() {
        return true;
    }

    static class SpitterMoveControl extends MoveControl {
        private final SpitterEntity fish;

        SpitterMoveControl(SpitterEntity owner) {
            super(owner);
            this.fish = owner;
        }

        public void tick() {
            if (this.fish.isSubmergedIn(FluidTags.WATER)) {
                this.fish.setVelocity(this.fish.getVelocity().add(0.0, 0.005, 0.0));
            }

            if (this.state == State.MOVE_TO && !this.fish.getNavigation().isIdle()) {
                float f = (float)(this.speed * this.fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.fish.setMovementSpeed(MathHelper.lerp(0.125F, this.fish.getMovementSpeed(), f));
                double d = this.targetX - this.fish.getX();
                double e = this.targetY - this.fish.getY();
                double g = this.targetZ - this.fish.getZ();
                if (e != 0.0) {
                    double h = Math.sqrt(d * d + e * e + g * g);
                    this.fish.setVelocity(this.fish.getVelocity().add(0.0, (double)this.fish.getMovementSpeed() * (e / h) * 0.1, 0.0));
                }

                if (d != 0.0 || g != 0.0) {
                    float i = (float)(MathHelper.atan2(g, d) * 57.2957763671875) - 90.0F;
                    this.fish.setYaw(this.wrapDegrees(this.fish.getYaw(), i, 90.0F));
                    this.fish.bodyYaw = this.fish.getYaw();
                }

            } else {
                this.fish.setMovementSpeed(0.0F);
            }
        }
    }

    static class SwimToRandomPlaceGoal extends SwimAroundGoal {
        private final SpitterEntity fish;

        public SwimToRandomPlaceGoal(SpitterEntity fish) {
            super(fish, 1.0, 40);
            this.fish = fish;
        }

        public boolean canStart() {
            return this.fish.hasSelfControl() && super.canStart();
        }
    }
}
