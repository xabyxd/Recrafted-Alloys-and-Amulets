package net.xabyxd.recrafted.registers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.xabyxd.recrafted.compat.nei.NEIRecipes;
import net.xabyxd.recrafted.utils.LogHelper;

public class RecipesRemover {

    private RecipesRemover() {}

    @SuppressWarnings("unchecked") // FIXED: NEI hidden recipes are now defined in NEIRrecipes and they are manually added there. (Server-Side issue)
        public static void removeVanillaRecipes() {
        NEIRecipes.defineAll();

        // ADDED: Furnace recipes can now be deleted and hidden from NEI
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        Iterator<Map.Entry<ItemStack, ItemStack>> smeltingIt = smelting.entrySet().iterator();
        while (smeltingIt.hasNext()) {
            if (NEIRecipes.isSmeltingInputHidden(smeltingIt.next().getKey())) {
                smeltingIt.remove();
            }
        }

        // ADDED: Crafting table recipes can now be deleted and hidden from NEI
        List<IRecipe> recipes = (List<IRecipe>) CraftingManager.getInstance().getRecipeList();
        int before = recipes.size();
        Iterator<IRecipe> recipeIt = recipes.iterator();
        while (recipeIt.hasNext()) {
            IRecipe recipe = recipeIt.next();
            try {
                if (NEIRecipes.isCraftingOutputHidden(recipe.getRecipeOutput())) {
                    recipeIt.remove();
                }
            } catch (Exception e) {
                LogHelper.warn("Skipping recipe " + recipe.getClass().getName(), e);
            }
        }
        LogHelper.info("Crafting recipes: {} -> {}", before, recipes.size());
    }
}