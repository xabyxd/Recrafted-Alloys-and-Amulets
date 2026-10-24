package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.utils.LogHelper;

public class RecipesRegister {

    public static void RecipesInit() {
        registerToolRecipes();
        registerMagicItemsRecipes();
        registerBrickRecipes();
        registerSmeltingRecipes();
        registerBlockRecipes();
        registerMaterialRecipes();
        registerMachineBlocksRecipes();
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

        // Iron Mortar (PLACEHOLDER)
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.ironMortar),
            " I ",
            "SIS",
            " S ",
            'I', "ingotIron",
            'S', Blocks.stone_slab)
        );
    }

    // ---------------------------------------------------------------------
    // Tools
    // ---------------------------------------------------------------------

    private static void registerMagicItemsRecipes() {
        if (!Compat.BaublesLoaded || !Compat.ThaumcraftLoaded) {
            LogHelper.warn("" + Recrafted.MODID + "] Magic items recipes disabled. Missing: "
                + (!Compat.BaublesLoaded ? "Baubles " : "")
                + (!Compat.ThaumcraftLoaded ? "Thaumcraft" : ""));
            return;
        }

        // Vigor Amulet
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(MagicRecraftedItems.vigorAmulet),
            " I ",
            "I I",
            " R ",
            'I', "ingotGold",
            'R', "gemRuby")
        );

        // Miner Amulet
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(MagicRecraftedItems.minerAmulet),
            " I ",
            "I I",
            " R ",
            'I', "ingotGold",
            'R', "gemSapphire")
        );

        // Speed Amulet
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(MagicRecraftedItems.speedAmulet),
            " I ",
            "I I",
            " R ",
            'I', "ingotGold",
            'R', "gemPeridot")
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

        // Unfired Coke Oven Brick
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedItems.unfiredCokeOvenBrick, 3),
            "CCC",
            "SMS",
            "SSS",
            'C', Items.clay_ball,
            'S', Blocks.sand,
            'M', new ItemStack(RecraftedItems.brickMold, 1, OreDictionary.WILDCARD_VALUE))
        );

        // Shapeless Unfired Clay Brick
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.unfiredClayBrick, 1),
            new ItemStack(RecraftedItems.brickMold, 1, OreDictionary.WILDCARD_VALUE),
            Items.clay_ball)
        );
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

        // Unfired Coke Oven Brick -> x1 Coke Oven Brick
        GameRegistry.addSmelting(RecraftedItems.unfiredCokeOvenBrick, new ItemStack(RecraftedItems.cokeOvenBrick), 0.7F);

        // TODO: Remake this recipe
        // Steel Ingot (PLACEHOLDER)
        GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(RecraftedItems.steelIngot), 0.0F);
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

        // Copper Block -> x9 Copper Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.copperIngot, 9),
            "blockCopper")
        );

        // Copper Ingot -> x9 Copper Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.copperNugget, 9),
            "ingotCopper")
        );

        // Tin Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.tinBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotTin")
        );

        // Tin Block -> x9 Tin Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.tinIngot, 9),
            "blockTin")
        );

        // Tin Ingot -> x9 Tin Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.tinNugget, 9),
            "ingotTin")
        );

        // Bronze Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.bronzeBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotBronze")
        );

        // Bronze Block -> x9 Bronze Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.bronzeIngot, 9),
            "blockBronze")
        );

        // Bronze Ingot -> x9 Bronze Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.bronzeNugget, 9),
            "ingotBronze")
        );

        // Silver Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.silverBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotSilver")
        );

        // Silver Block -> x9 Silver Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.silverIngot, 9),
            "blockSilver")
        );

        // Silver Ingot -> x9 Silver Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.silverNugget, 9),
            "ingotSilver")
        );

        // Lead Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.leadBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotLead")
        );

        // Lead Block -> x9 Lead Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.leadIngot, 9),
            "blockLead")
        );

        // Lead Ingot -> x9 Lead Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.leadNugget, 9),
            "ingotLead")
        );

        // Steel Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.steelBlock),
            "SSS",
            "SSS",
            "SSS",
            'S', "ingotSteel")
        );

        // Steel Block -> x9 Steel Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.steelIngot, 9),
            "blockSteel")
        );

        // Steel Ingot -> x9 Steel Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.steelNugget, 9),
            "ingotSteel")
        );

        // Iron Ingot -> x9 Iron Nugget
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.ironNugget, 9),
            "ingotIron")
        );
    }

    // ---------------------------------------------------------------------
    // Materials
    // ---------------------------------------------------------------------

    private static void registerMaterialRecipes() {
        // x3 Copper Ingot + x1 Tin Ingot = x4 Bronze Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(RecraftedItems.bronzeIngot, 4),
            "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin")
        );
    }

    // ---------------------------------------------------------------------
    // Machines
    // ---------------------------------------------------------------------

    private static void registerMachineBlocksRecipes() {
        // TODO: Remake this recipe
        // Coke Oven Controller (PLACEHOLDER)
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.cokeOvenController),
            " C ",
            "CMC",
            " C ",
            'C', new ItemStack(RecraftedItems.cokeOvenBrick, 1, OreDictionary.WILDCARD_VALUE),
            'M', Blocks.iron_bars)
        );

        // TODO: Remake this recipe
        // Coke Oven Bricks (PLACEHOLDER)
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(RecraftedBlocks.cokeOvenBricks),
            "BB",
            "BB",
            'B', new ItemStack(RecraftedItems.cokeOvenBrick, 1, OreDictionary.WILDCARD_VALUE))
        );
    }
}