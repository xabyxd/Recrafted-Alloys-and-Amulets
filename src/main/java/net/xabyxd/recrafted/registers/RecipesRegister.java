package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipesRegister {
    
    public static void RecipesInit() {
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
            " S ",
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

        // Registered in NEIRecipes.defineAll() too
        // Unfired Clay Brick -> x1 Brick
        GameRegistry.addSmelting(RecraftedItems.unfiredClayBrick, new ItemStack(Items.brick), 0.7F);

        // Copper Ore -> Copper Ingot
        GameRegistry.addSmelting(RecraftedBlocks.copperOre, new ItemStack(RecraftedItems.copperIngot), 0.7F);

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
    }
}