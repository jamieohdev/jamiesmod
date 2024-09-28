package com.jamiedev.mod.datagen;
import com.jamiedev.mod.init.JamiesModBlocks;
import com.jamiedev.mod.init.JamiesModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
public class JamiesModModelProvider  extends FabricModelProvider {
    public JamiesModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(JamiesModBlocks.MALACHITE);
        blockStateModelGenerator.registerSimpleCubeAll(JamiesModBlocks.MALACHITE_TILE);
        blockStateModelGenerator.registerAxisRotated(JamiesModBlocks.MALACHITE_CHISELED, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
        blockStateModelGenerator.registerAxisRotated(JamiesModBlocks.MALACHITE_PILLAR, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
        blockStateModelGenerator.registerDoor(JamiesModBlocks.MALACHITE_DOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}