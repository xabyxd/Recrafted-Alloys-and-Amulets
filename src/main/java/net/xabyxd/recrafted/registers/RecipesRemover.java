package net.xabyxd.recrafted.registers;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipesRemover {

    private RecipesRemover() {}

    @SuppressWarnings("unchecked")
    public static void removeVanillaRecipes() {
        // Furnace => deletes all smelting recipes that inputs clay balls
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        Iterator<Map.Entry<ItemStack, ItemStack>> smeltingIt = smelting.entrySet().iterator();
        while (smeltingIt.hasNext()) {
            ItemStack input = smeltingIt.next().getKey();
            if (input != null && input.getItem() == Items.clay_ball) {
                smeltingIt.remove();
            }
        }

        /* EXAMPLE CODE
        @SuppressWarnings("unchecked")
        public static void removeVanillaRecipes() {
        // Furnace => deletes all smelting recipes that output brick
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        Iterator<Map.Entry<ItemStack, ItemStack>> smeltingIt = smelting.entrySet().iterator();
        while (smeltingIt.hasNext()) {
            ItemStack output = smeltingIt.next().getValue();
            if (output != null && output.getItem() == Items.brick) {
                smeltingIt.remove();
            }
        }

        // Crafting table => deletes all recipes that output stone pickaxe
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> recipeIt = recipes.iterator();
        while (recipeIt.hasNext()) {
            ItemStack output = recipeIt.next().getRecipeOutput();
            if (output != null && output.getItem() == Items.stone_pickaxe) {
                recipeIt.remove();
            }
        }
        */
    }
}