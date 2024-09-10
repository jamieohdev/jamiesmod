package com.jamiedev.mod.datagen;
import com.jamiedev.mod.init.JamiesModBlocks;
import com.jamiedev.mod.init.JamiesModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
public class JamiesModRecipeProvider  extends FabricRecipeProvider {
    public JamiesModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    //examples:
    ////https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.20.X/blob/a1ce57390adb5ef0f1cd6ba7a0bf6b1b659074f5/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRecipeProvider.java



    @Override
    public void generate(RecipeExporter exporter) {


        WoodRecipe(exporter, JamiesModBlocks.STRIPPED_ANCIENT_LOG, JamiesModBlocks.STRIPPED_ANCIENT_WOOD, RecipeCategory.BUILDING_BLOCKS);
        offerShapelessRecipe(exporter,JamiesModBlocks.ANCIENT_BUTTON, JamiesModBlocks.ANCIENT_PLANKS, "wooden_button",1);
        DoorRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_DOOR, RecipeCategory.REDSTONE, "wooden_door");
        FenceRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_FENCE, RecipeCategory.MISC,"wooden_fence");
        FenceGateRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_FENCE_GATE, RecipeCategory.MISC, "wooden_fence_gate");
        HanginSignRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModItems.ANCIENT_HANGING_SIGN, RecipeCategory.MISC, "hanging_sign");
        offerPlanksRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModTags.Items.ANCIENT_LOGS, 4);
        SignRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModItems.ANCIENT_SIGN, RecipeCategory.MISC, "wooden_sign");
        SlabRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_SLAB);
        StairsRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_STAIRS);
        TrapDoorRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_TRAPDOOR, RecipeCategory.REDSTONE, "wooden_trapdoor");
        WoodRecipe(exporter, JamiesModBlocks.ANCIENT_LOG, JamiesModBlocks.ANCIENT_WOOD, RecipeCategory.BUILDING_BLOCKS);
        PressurePlateRecipe(exporter, JamiesModBlocks.ANCIENT_PLANKS, JamiesModBlocks.ANCIENT_PRESSURE_PLATE, RecipeCategory.REDSTONE, "wooden_pressure_plate");



    }

    void SlabRecipe(RecipeExporter exporter, Block input, Block output){
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("XXX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));

    }


    void StairsRecipe(RecipeExporter exporter, Block input, Block output){
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X  ")
                .pattern("XX ")
                .pattern("XXX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void CraftingTableCoatingRecipe(RecipeExporter exporter, Item uncoated, Item coated, RecipeCategory recipeCategory, String group){
        ShapelessRecipeJsonBuilder.create(recipeCategory, coated)
                .input(JamiesModItems.JAMIES_ITEM)
                .input(uncoated)
                .criterion(hasItem(uncoated.asItem()), conditionsFromItem(uncoated.asItem()))
                .group(group)
                .offerTo(exporter, Identifier.of(getRecipeName(coated.asItem()) + "_from_item"));
    }

    void PressurePlateRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 1)
                .pattern("XX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group(group)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void ChiseledRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 1)
                .pattern("X")
                .pattern("X")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void WoodRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 3)
                .pattern("XX")
                .pattern("XX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group("bark")
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void DoorRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 3)
                .pattern("XX")
                .pattern("XX")
                .pattern("XX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group(group)
                .showNotification(true)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void FenceRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 3)
                .pattern("WXW")
                .pattern("WXW")
                .input('X', Items.STICK)
                .input('W', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group(group)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void FenceGateRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 1)
                .pattern("XWX")
                .pattern("XWX")
                .input('X', Items.STICK)
                .input('W', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group(group)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void HanginSignRecipe(RecipeExporter exporter, Block input, Item output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 1)
                .pattern("X X")
                .pattern("WWW")
                .pattern("WWW")
                .input('X', Blocks.CHAIN)
                .input('W', input.asItem())
                .criterion(hasItem(input), conditionsFromItem(input))
                .group(group)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void SignRecipe(RecipeExporter exporter, Block input, Item output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 3)
                .pattern("WWW")
                .pattern("WWW")
                .pattern(" X ")
                .input('X', Items.STICK)
                .input('W', input.asItem())
                .criterion(hasItem(input), conditionsFromItem(input))
                .group(group)
                .showNotification(true)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

    void TrapDoorRecipe(RecipeExporter exporter, Block input, Block output, RecipeCategory recipeCategory, String group){
        ShapedRecipeJsonBuilder.create(recipeCategory, output, 2)
                .pattern("XXX")
                .pattern("XXX")
                .input('X', input.asItem())
                .criterion(hasItem(input.asItem()), conditionsFromItem(input.asItem()))
                .group(group)
                .showNotification(true)
                .offerTo(exporter, Identifier.of(getRecipeName(output.asItem())));
    }

}