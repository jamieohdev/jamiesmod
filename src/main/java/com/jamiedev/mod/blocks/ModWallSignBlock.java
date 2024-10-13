package com.jamiedev.mod.blocks;

import com.jamiedev.mod.blocks.entity.ModSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
}