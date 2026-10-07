package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipesRegister {
    
    public static void RecipesInit() {
        // Copper Pickaxe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.copperPickaxe),
            "CCC",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Axe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.copperAxe),
            "CC ",
            "CS ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Sword
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.copperSword),
            " C ",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Shovel
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.copperShovel),
            " C ",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Copper Hoe
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.copperHoe),
            "CC ",
            " S ",
            " S ",
            'C', "ingotCopper",
            'S', "stickWood")
        );

        // Brick Mold
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModItems.brickMold),
            "PPP",
            "S S",
            "PPP",
            'P', "plankWood",
            'S', "stickWood")
        );

        // Copper Ore -> Copper Ingot
        GameRegistry.addSmelting(ModBlocks.copperOre, new ItemStack(ModItems.copperIngot), 0.7F);

        // Copper Block
        GameRegistry.addRecipe(new ShapedOreRecipe(
            new ItemStack(ModBlocks.copperBlock),
            "CCC",
            "CCC",
            "CCC",
            'C', "ingotCopper")
        );

        // Copper Block -> x9 Copper Ingot
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(ModItems.copperIngot, 9),
            "blockCopper")
        );
    }
}