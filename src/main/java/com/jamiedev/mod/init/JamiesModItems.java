package com.jamiedev.mod.init;
import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.items.JamiesModItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class JamiesModItems
{
    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, JamiesMod.getModId(id), item);
    }

    public static final Item JAMIES_ITEM = registerItem("jamies_item", new Item(new Item.Settings().fireproof()));

    public static final Item ANCIENT_SIGN = registerItem("ancient_sign",
            new SignItem(new Item.Settings().maxCount(16), JamiesModBlocks.ANCIENT_SIGN, JamiesModBlocks.ANCIENT_WALL_SIGN));

    public static final Item ANCIENT_HANGING_SIGN = registerItem("ancient_hanging_sign",
            new HangingSignItem(JamiesModBlocks.ANCIENT_HANGING_SIGN, JamiesModBlocks.ANCIENT_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));


    public static final Item ANCIENT_BOAT = registerItem("ancient_boat", (Item)(new BoatItem(false, net.minecraft.entity.vehicle.BoatEntity.Type.OAK, (new Item.Settings()).maxCount(1))));
    public static final Item ANCIENT_CHEST_BOAT = registerItem("ancient_chest_boat", (Item)(new BoatItem(true, net.minecraft.entity.vehicle.BoatEntity.Type.OAK, (new Item.Settings()).maxCount(1))));


//    public static final Item MUSIC_DISC_MOLTEN = registerItem("music_disc_molten", new Item(new Item.Settings().rarity(Rarity.RARE).jukeboxPlayable(JukeboxSongs.CREATOR_MUSIC_BOX).maxCount(1)));
//    public static final Item DISC_FRAGMENT_MOLTEN = registerItem("disc_fragment_molten", new DiscFragmentItem(new Item.Settings()));


    public static void addItemsToItemGroup() {

        addToItemGroup(JamiesModItemGroup.JAMIES_MOD, JAMIES_ITEM);
        addToItemGroup(JamiesModItemGroup.JAMIES_MOD, ANCIENT_SIGN);
        addToItemGroup(JamiesModItemGroup.JAMIES_MOD, ANCIENT_HANGING_SIGN);
    }


    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add((item)));
    }

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            //entries.addBefore(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
           // entries.addAfter(Items.RAW_IRON_BLOCK, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
           // entries.addAfter(Items.RAW_IRON, PigsteelItems.PIGSTEEL_CHUNK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
//            entries.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_JAMIE);
        });


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(JamiesModBlocks.JAMIES_BLOCK.asItem());

        });

        addItemsToItemGroup();
    }
}
