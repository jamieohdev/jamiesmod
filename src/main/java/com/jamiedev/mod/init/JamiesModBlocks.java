package com.jamiedev.mod.init;

import java.util.Optional;
import com.jamiedev.mod.*;

import com.jamiedev.mod.blocks.*;
import com.jamiedev.mod.items.JamiesModItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
public class JamiesModBlocks {

    // public static final Woodset FIR = new Woodset(JamiesMod.getModId("fir"), MapColor.DEEPSLATE_GRAY, MapColor.SPRUCE_BROWN, Woodset.WoodPreset.DEFAULT);
    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, JamiesMod.getModId(blockID), new BlockItem(block, new Item.Settings().fireproof()));
    }

    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, JamiesMod.getModId(blockID), block);
    }
    Blocks blocks; // using this as a reference

    public static final Block JAMIES_BLOCK = createBlockWithItem("jamies_block", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7),
            AbstractBlock.Settings.copy(Blocks.OBSIDIAN).strength(52.0F, 1200.0F).instrument(NoteBlockInstrument.BANJO).pistonBehavior(PistonBehavior.NORMAL)));

    public static final Block LIMBOSTONE = registerBlock("limbostone",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block LIMBOSLATE = registerBlock("limboslate",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)),  JamiesModItemGroup.JAMIES_MOD);

    public static final Block CLOUD = registerBlock("cloud",
            new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).strength(0.001F).slipperiness(0.989F).sounds(BlockSoundGroup.WOOL)), JamiesModItemGroup.JAMIES_MOD);

    public static  final  Block RAFFLESIA = registerBlock("rafflesia", new RafflesiaBlock(AbstractBlock.Settings.create().mapColor(MapColor.DULL_PINK).breakInstantly().noCollision().sounds(BlockSoundGroup.SPORE_BLOSSOM).pistonBehavior(PistonBehavior.DESTROY)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block GOURD_LANTERN = registerBlock("gourd_lantern", new LanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_DULL_PINK).solid().requiresTool().strength(0.2F).sounds(BlockSoundGroup.SHROOMLIGHT).luminance((state) -> {
        return 15;
    }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_ROOTS = registerBlock("ancient_roots",
            new AncientRootBlock(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).instrument(NoteBlockInstrument.BASS).strength(0.7F).sounds(BlockSoundGroup.MANGROVE_ROOTS).nonOpaque().suffocates(Blocks::never).blockVision(Blocks::never).nonOpaque().burnable()), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_VINE = registerBlock("ancient_vine",
            new VineBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).replaceable().noCollision().ticksRandomly().strength(0.2F).sounds(BlockSoundGroup.VINE).burnable().pistonBehavior(PistonBehavior.DESTROY)),  JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_SAPLING = createBlockWithItem("ancient_sapling", new SaplingBlock(new SaplingGenerator(JamiesMod.getModId( "ancient_tree").toString(),
            Optional.empty(),
            Optional.of(JamiesModConfiguredFeatures.ANCIENT_TREE),
            Optional.empty()),AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block ANCIENT_LOG = registerBlock("ancient_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);
    public static final Block ANCIENT_LEAVES = registerBlock("ancient_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)), JamiesModItemGroup.JAMIES_MOD);
    public static final Block ANCIENT_WOOD = registerBlock("ancient_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block STRIPPED_ANCIENT_LOG = registerBlock("stripped_ancient_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block STRIPPED_ANCIENT_WOOD = registerBlock("stripped_ancient_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_PLANKS = registerBlock("ancient_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_STAIRS = registerBlock("ancient_stairs",
            new StairsBlock(ANCIENT_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_SLAB = registerBlock("ancient_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_FENCE = registerBlock("ancient_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_FENCE_GATE = registerBlock("ancient_fence_gate",
            new FenceGateBlock(JamiesModWoodType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_DOOR = registerBlock("ancient_door",
            new DoorBlock(JamiesModBlockSetType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_TRAPDOOR = registerBlock("ancient_trapdoor",
            new TrapdoorBlock(JamiesModBlockSetType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).strength(2.0f)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_PRESSURE_PLATE = registerBlock("ancient_pressure_plate",
            new PressurePlateBlock(JamiesModBlockSetType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)), JamiesModItemGroup.JAMIES_MOD);
    public static final Block ANCIENT_BUTTON = registerBlock("ancient_button",
            new ButtonBlock(JamiesModBlockSetType.ANCIENT, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_SIGN = registerBlockWithoutBlockItem("ancient_sign",
            new SignBlock(JamiesModWoodType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_SIGN).strength(1.0f).solid()), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_WALL_SIGN = registerBlockWithoutBlockItem("ancient_wall_sign",
            new WallSignBlock(JamiesModWoodType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).strength(1.0f).dropsLike(JamiesModBlocks.ANCIENT_SIGN).solid()), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_HANGING_SIGN = registerBlockWithoutBlockItem("ancient_hanging_sign",
            new HangingSignBlock(JamiesModWoodType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN).strength(1.0f).solid()), JamiesModItemGroup.JAMIES_MOD);

    public static final Block ANCIENT_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("ancient_wall_hanging_sign",
            new WallHangingSignBlock(JamiesModWoodType.ANCIENT, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).strength(1.0f).dropsLike(ANCIENT_HANGING_SIGN).solid()), JamiesModItemGroup.JAMIES_MOD);

    private static Block registerBlock(String name, Block block, RegistryKey<ItemGroup> group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, JamiesMod.getModId( name), block);
    }


    private static Block registerBlockWithoutBlockItem(String name, Block block, RegistryKey<ItemGroup> group) {
        return Registry.register(Registries.BLOCK, JamiesMod.getModId(name), block);
    }

    private static Item registerBlockItem(String name, Block block, RegistryKey<ItemGroup> group) {

        Item item = Registry.register(Registries.ITEM, JamiesMod.getModId( name),
                new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    private static boolean always(BlockState p_50775_, BlockPos p_50777_) {
        return true;
    }

    private static boolean never(BlockState p_50806_, BlockPos p_50808_) {
        return false;
    }
    
    public static void init()
    {
        JamiesModStrippableBlocks.registerStrippables();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(Items.LILY_OF_THE_VALLEY, JAMIES_BLOCK));
    }
}
