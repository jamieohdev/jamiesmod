package com.jamiedev.mod.blocks.entity;

import com.jamiedev.mod.blocks.PrimordialVentBlock;
import com.jamiedev.mod.init.JamiesModBlockEntities;
import com.jamiedev.mod.init.JamiesModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.util.Clearable;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class PrimordialVentEntity  extends BlockEntity implements Clearable
{

    CampfireBlockEntity ref;

    FurnaceBlock ref2;

    public PrimordialVentEntity(BlockPos pos, BlockState state) {
        super(JamiesModBlockEntities.PRIMORDIAL_VENT, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, PrimordialVentEntity campfire) {
        Random random = world.random;
        int i;

        if (random.nextFloat() < 0.11F) {
            for(i = 0; i < random.nextInt(2) + 2; ++i) {
                PrimordialVentBlock.spawnSmokeParticle(world, pos, false);
            }
        }

    }

    public static void litServerTick(World world, BlockPos pos, BlockState state, PrimordialVentEntity campfire) {


    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
    }

    @Override
    public void clear() {

    }
}
