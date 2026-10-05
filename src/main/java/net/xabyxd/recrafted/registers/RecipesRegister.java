package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
    }
}