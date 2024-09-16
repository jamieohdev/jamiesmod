package com.jamiedev.mod.init;
import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.items.HookItem;
import com.jamiedev.mod.items.JamiesModItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class JamiesModItems
{
    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, JamiesMod.getModId(id), item);
    }

    Items items;

    public static final Item JAMIES_ITEM = registerItem("jamies_item", new Item(new Item.Settings().fireproof()));
    public static final Item HOOK = registerItem("hook", new HookItem(new Item.Settings().maxCount(1)));

    public static final Item ANCIENT_SIGN = registerItem("ancient_sign",
            new SignItem(new Item.Settings().maxCount(16), JamiesModBlocks.ANCIENT_SIGN, JamiesModBlocks.ANCIENT_WALL_SIGN));

    public static final Item ANCIENT_HANGING_SIGN = registerItem("ancient_hanging_sign",
            new HangingSignItem(JamiesModBlocks.ANCIENT_HANGING_SIGN, JamiesModBlocks.ANCIENT_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));


    public static final Item ANCIENT_BOAT = registerItem("ancient_boat", (Item)(new BoatItem(false, net.minecraft.entity.vehicle.BoatEntity.Type.OAK, (new Item.Settings()).maxCount(1))));
    public static final Item ANCIENT_CHEST_BOAT = registerItem("ancient_chest_boat", (Item)(new BoatItem(true, net.minecraft.entity.vehicle.BoatEntity.Type.OAK, (new Item.Settings()).maxCount(1))));

    public static final Item GOURD_ON_A_STICK =  registerItem("gourd_on_a_stick", (Item)(new OnAStickItem((new Item.Settings()).maxDamage(100), JamiesModEntityTypes.BRUNGLE, 1)));
    public static final Item GOURD_FLESH = registerItem("gourd_flesh", new Item((new Item.Settings()).food(FoodComponents.GOLDEN_CARROT)));
    public static final Item GOURD_SEEDS = registerItem("gourd_seeds", (Item)(new AliasedBlockItem(JamiesModBlocks.GOURD_LANTERN, new Item.Settings())));

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

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {

        });


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(JamiesModBlocks.JAMIES_BLOCK.asItem());

        });

        addItemsToItemGroup();
    }
}
