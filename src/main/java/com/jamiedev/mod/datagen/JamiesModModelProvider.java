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

        BlockStateModelGenerator.BlockTexturePool valtroxPlankPool = blockStateModelGenerator.registerCubeAllModelTexturePool(JamiesModBlocks.ANCIENT_PLANKS);


        valtroxPlankPool.fenceGate(JamiesModBlocks.ANCIENT_FENCE_GATE);
        valtroxPlankPool.family(BlockFamilies.register(JamiesModBlocks.ANCIENT_PLANKS).sign(JamiesModBlocks.ANCIENT_SIGN, JamiesModBlocks.ANCIENT_WALL_SIGN).build());
        blockStateModelGenerator.registerHangingSign(JamiesModBlocks.STRIPPED_ANCIENT_LOG, JamiesModBlocks.ANCIENT_HANGING_SIGN, JamiesModBlocks.ANCIENT_WALL_HANGING_SIGN);
        blockStateModelGenerator.registerDoor(JamiesModBlocks.ANCIENT_DOOR);
        blockStateModelGenerator.registerWallPlant(JamiesModBlocks.ANCIENT_VINE);
        blockStateModelGenerator.registerTrapdoor(JamiesModBlocks.ANCIENT_TRAPDOOR);
        blockStateModelGenerator.registerSingleton(JamiesModBlocks.ANCIENT_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerSingleton(JamiesModBlocks.ANCIENT_ROOTS, TexturedModel.LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(JamiesModBlocks.ANCIENT_SAPLING, Blocks.POTTED_ACACIA_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(JamiesModBlocks.ANCIENT_LOG).log(JamiesModBlocks.ANCIENT_LOG).wood(JamiesModBlocks.ANCIENT_WOOD);
        blockStateModelGenerator.registerLog(JamiesModBlocks.STRIPPED_ANCIENT_LOG).log(JamiesModBlocks.STRIPPED_ANCIENT_LOG).wood(JamiesModBlocks.STRIPPED_ANCIENT_WOOD);
        valtroxPlankPool.pressurePlate(JamiesModBlocks.ANCIENT_PRESSURE_PLATE);
        valtroxPlankPool.stairs(JamiesModBlocks.ANCIENT_STAIRS);
        valtroxPlankPool.slab(JamiesModBlocks.ANCIENT_SLAB);
        valtroxPlankPool.fence(JamiesModBlocks.ANCIENT_FENCE);
        valtroxPlankPool.button(JamiesModBlocks.ANCIENT_BUTTON);
        blockStateModelGenerator.registerSimpleCubeAll(JamiesModBlocks.LIMBOSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(JamiesModBlocks.LIMBOSLATE);
        blockStateModelGenerator.registerSimpleCubeAll(JamiesModBlocks.CLOUD);
        blockStateModelGenerator.registerSimpleState(JamiesModBlocks.GOURD_LANTERN);
        blockStateModelGenerator.registerSimpleState(JamiesModBlocks.RAFFLESIA);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(JamiesModItems.ANCIENT_SIGN, Models.GENERATED);
        //itemModelGenerator.register(JamiesModItems.ANCIENT_HANGING_SIGN, Models.GENERATED);
        itemModelGenerator.register(JamiesModItems.ANCIENT_BOAT, Models.GENERATED);
        itemModelGenerator.register(JamiesModItems.ANCIENT_CHEST_BOAT, Models.GENERATED);
    }
}