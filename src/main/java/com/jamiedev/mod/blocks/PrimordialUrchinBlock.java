package com.jamiedev.mod.blocks;

import com.jamiedev.mod.blocks.entity.PrimordialUrchinEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class PrimordialUrchinBlock extends BlockWithEntity implements Waterloggable
{
    public static final MapCodec<PrimordialUrchinBlock> CODEC = createCodec(PrimordialUrchinBlock::new);
    public static final IntProperty ACTIVATED;
    public static BooleanProperty ACTIVATEDBOOL;
    public static final VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED;

    public static boolean test = false;

    FlowerBlock ref;


    public MapCodec<PrimordialUrchinBlock> getCodec() { return CODEC; }

    public PrimordialUrchinBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(ACTIVATED, 0)).with(ACTIVATEDBOOL, false).with(WATERLOGGED, false));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    protected static boolean isInWater(BlockState state, BlockView world, BlockPos pos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            return true;
        } else {
            Direction[] var3 = Direction.values();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Direction direction = var3[var5];
                if (world.getFluidState(pos.offset(direction)).isIn(FluidTags.WATER)) {
                    return true;
                }
            }

            return false;
        }
    }
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isInWater(state, world, pos)) {
            world.setBlockState(pos, (BlockState)this.getDefaultState().with(WATERLOGGED, false), 2);
        }

    }

    private static void updateState(BlockState state, World world, BlockPos pos) {

        boolean bl = (Boolean)state.get(ACTIVATEDBOOL);

        if (world.random.nextInt(1000) == 1)
        {
            world.setBlockState(pos, (BlockState)state.with(ACTIVATED, 0), 3);
        }

        if (test) {
            world.setBlockState(pos, (BlockState)state.with(ACTIVATED, 1), 3);
        }

    }

    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState blockState = (BlockState)((BlockState)this.getDefaultState()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        return blockState;
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity livingEntity) {
                if (!livingEntity.isInvulnerableTo(world.getDamageSources().cactus()) && !livingEntity.isInCreativeMode()) {
                    world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.ENTITY_PUFFER_FISH_BLOW_UP,
                            SoundCategory.BLOCKS, 0.5F + world.random.nextFloat(), world.random.nextFloat() * 0.7F + 0.6F, false);
                    BlockState blockState = (BlockState)state.cycle(ACTIVATEDBOOL);
                    world.setBlockState(pos, blockState, 2);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity, blockState));
                    updateState(blockState, world, pos);
                    test = true;
                    entity.damage(world.getDamageSources().cactus(), 1.0F);
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10));

                }
            }

        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PrimordialUrchinEntity(pos, state);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{ACTIVATEDBOOL, ACTIVATED, WATERLOGGED});
    }

    protected FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        ACTIVATED = Properties.POWER;
        ACTIVATEDBOOL = Properties.INVERTED;
        SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    }
}
