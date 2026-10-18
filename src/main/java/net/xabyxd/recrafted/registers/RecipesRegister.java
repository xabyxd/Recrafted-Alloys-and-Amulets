package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipesRegister {

    public static void RecipesInit() {
        registerToolRecipes();
        registerBrickRecipes();
        registerSmeltingRecipes();
        registerBlockRecipes();
        registerMaterialRecipes();
    }

    // ---------------------------------------------------------------------
    // Tools
    // ---------------------------------------------------------------------

    private static void registerToolRecipes() {
        // Copper Pickaxe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.copperPickaxe),
            "CCC",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Axe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.copperAxe),
            "CC ",
            "CS ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Sword
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.copperSword),
            " C ",
            " C ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Shovel
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.copperShovel),
            " C ",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Hoe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.copperHoe),
            "CC ",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );
    }

    // ---------------------------------------------------------------------
    // Bricks
    // ---------------------------------------------------------------------

    private static void registerBrickRecipes() {
        // Brick Mold
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.brickMold),
            "PPP",
            "S S",
            "PPP",
            'P', "plankWood",
            'S', "stickWood")
        );

        // Unfired Clay Brick
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.unfiredClayBrick, 8),
            "CCC",
            "CSC",
            "CCC",
            'C', Items.clay_ball,
            'S', new ItemStack(RecraftedItems.brickMold, 1, OreDictionary.WILDCARD_VALUE))
        );

        // Shapeless Unfired Clay Brick
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.unfiredClayBrick, 1),
            new ItemStack(RecraftedItems.brickMold, 1, OreDictionary.WILDCARD_VALUE),
            Items.clay_ball
        ));
    }

    // ---------------------------------------------------------------------
    // Smelting
    // ---------------------------------------------------------------------

    private static void registerSmeltingRecipes() {
        // Registered in NEIRecipes.defineAll() too
        // Unfired Clay Brick -> x1 Brick
        GameRegistry.addSmelting(RecraftedItems.unfiredClayBrick, new ItemStack(Items.brick), 0.7F);

        // Copper Ore -> Copper Ingot
        GameRegistry.addSmelting(RecraftedBlocks.copperOre, new ItemStack(RecraftedItems.copperIngot), 0.7F);

        // Tin Ore -> Tin Ingot
        GameRegistry.addSmelting(RecraftedBlocks.tinOre, new ItemStack(RecraftedItems.tinIngot), 0.7F);

        // Silver Ore -> Silver Ingot
        GameRegistry.addSmelting(RecraftedBlocks.silverOre, new ItemStack(RecraftedItems.silverIngot), 0.7F);

        // Lead Ore -> Lead Ingot
        GameRegistry.addSmelting(RecraftedBlocks.leadOre, new ItemStack(RecraftedItems.leadIngot), 0.7F);
    }

    // ---------------------------------------------------------------------
    // Blocks
    // ---------------------------------------------------------------------

    private static void registerBlockRecipes() {
        // Copper Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.copperBlock),
            "CCC",
            "CCC",
            "CCC",
            'C', "ingotCopper")
        );

        // Tin Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.tinBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotTin")
        );

        // Bronze Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.bronzeBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotBronze")
        );

        // Silver Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.silverBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotSilver")
        );

        // Lead Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.leadBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotLead")
        );

        // Steel Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.steelBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotSteel")
        );
    }

    // ---------------------------------------------------------------------
    // Materials
    // ---------------------------------------------------------------------

    private static void registerMaterialRecipes() {
        // x3 Copper Ingot + x1 Tin Ingot = x4 Bronze Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.bronzeIngot, 4),
            "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"
        ));

        // Copper Block -> x9 Copper Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.copperIngot, 9),
            "blockCopper")
        );
    }
}