package com.jamiedev.mod.datagen;
import com.jamiedev.mod.init.JamiesModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
public class JamiesModLootTableProvider  extends FabricBlockLootTableProvider {


    public JamiesModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(JamiesModBlocks.STRIPPED_ANCIENT_LOG, JamiesModBlocks.STRIPPED_ANCIENT_LOG.asItem());
        addDrop(JamiesModBlocks.STRIPPED_ANCIENT_WOOD, JamiesModBlocks.STRIPPED_ANCIENT_WOOD.asItem());
        addDrop(JamiesModBlocks.ANCIENT_PRESSURE_PLATE, drops(JamiesModBlocks.ANCIENT_PRESSURE_PLATE.asItem()));


        addDrop(JamiesModBlocks.ANCIENT_LOG, JamiesModBlocks.ANCIENT_LOG.asItem());
        addDrop(JamiesModBlocks.ANCIENT_WOOD, JamiesModBlocks.ANCIENT_WOOD.asItem());
        addDrop(JamiesModBlocks.STRIPPED_ANCIENT_LOG, JamiesModBlocks.STRIPPED_ANCIENT_LOG.asItem());
        addDrop(JamiesModBlocks.STRIPPED_ANCIENT_WOOD, JamiesModBlocks.STRIPPED_ANCIENT_WOOD.asItem());
        addDrop(JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_PLANKS.asItem());
        addDrop(JamiesModBlocks.ANCIENT_STAIRS, JamiesModBlocks.ANCIENT_STAIRS.asItem());
        addDrop(JamiesModBlocks.ANCIENT_SLAB, JamiesModBlocks.ANCIENT_SLAB.asItem());
        addDrop(JamiesModBlocks.ANCIENT_FENCE, JamiesModBlocks.ANCIENT_FENCE.asItem());
        addDrop(JamiesModBlocks.ANCIENT_FENCE_GATE, JamiesModBlocks.ANCIENT_FENCE_GATE.asItem());
        addDrop(JamiesModBlocks.ANCIENT_TRAPDOOR, JamiesModBlocks.ANCIENT_TRAPDOOR.asItem());
        addDrop(JamiesModBlocks.ANCIENT_PRESSURE_PLATE, drops(JamiesModBlocks.ANCIENT_PRESSURE_PLATE.asItem()));
        addDrop(JamiesModBlocks.ANCIENT_BUTTON, drops(JamiesModBlocks.ANCIENT_BUTTON.asItem()));


        addDrop(JamiesModBlocks.LIMBOSLATE, JamiesModBlocks.LIMBOSLATE.asItem());
        addDrop(JamiesModBlocks.LIMBOSTONE, JamiesModBlocks.LIMBOSTONE.asItem());
        addDrop(JamiesModBlocks.SAGARIA, JamiesModBlocks.SAGARIA.asItem());
        addDrop(JamiesModBlocks.MONTSECHIA, JamiesModBlocks.MONTSECHIA.asItem());
        addDrop(JamiesModBlocks.CLAYSTONE, JamiesModBlocks.CLAYSTONE.asItem());
        addDrop(JamiesModBlocks.COARSE_CLAYSTONE, JamiesModBlocks.COARSE_CLAYSTONE.asItem());
        addDrop(JamiesModBlocks.BYSTONE, JamiesModBlocks.BYSTONE.asItem());
        addDrop(JamiesModBlocks.RAFFLESIA, JamiesModBlocks.RAFFLESIA.asItem());

    }

    public LootTable.Builder mushroomBlockDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch) {
        return this.dropsWithSilkTouch(withSilkTouch, this.applyExplosionDecay(withSilkTouch,
                ItemEntry.builder(withoutSilkTouch)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-6.0F, 2.0F)))
                        .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMin(0)))));
    }

    public LootTable.Builder nameableContainerDrops(Block drop) {
        return LootTable.builder().pool(this.addSurvivesExplosionCondition(drop, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(drop).apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY)))));
    }
}