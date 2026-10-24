package net.xabyxd.recrafted.registers.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.registers.RecraftedItems;

/**
 * Recipe registry of the coke oven. One input item produces one output item after a given time.
 * The oven only asks this class "which recipe does this item have?", and NEI can read the same list,
 * so recipes are defined in a single place.
 */
public class CokeOvenRecipes {

    /** A single coke oven recipe. Instances are created through {@link CokeOvenRecipes#addRecipe}. */
    public static class Recipe {
        /** Required input; its stackSize is the amount consumed per craft. */
        public final ItemStack input;
        /** Produced item; its stackSize is the amount produced per craft. */
        public final ItemStack output;
        /** Cooking time in ticks (max 32767, it is synced to the client as a short). */
        public final int time;

        private Recipe(ItemStack input, ItemStack output, int time) {
            this.input = input;
            this.output = output;
            this.time = time;
        }

        /** Checks whether an item is the input of this recipe. The stack size is ignored. */
        public boolean matches(ItemStack stack) {
            return stack != null
                && stack.getItem() == input.getItem()
                && (input.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack.getItemDamage() == input.getItemDamage());
        }
    }

    private static final List<Recipe> RECIPES = new ArrayList<Recipe>();

    /**
     * Registers a recipe.
     *
     * @param input  item to cook; use OreDictionary.WILDCARD_VALUE as damage to accept any metadata
     * @param output item produced
     * @param time   cooking time in ticks (20 ticks = 1 second)
     */
    public static void addRecipe(ItemStack input, ItemStack output, int time) {
        RECIPES.add(new Recipe(input.copy(), output.copy(), time));
    }

    /** Returns the recipe whose input matches the item (ignoring the stack size), or null if there is none. */
    public static Recipe getRecipe(ItemStack stack) {
        if (stack == null) return null;
        for (Recipe recipe : RECIPES) if (recipe.matches(stack)) return recipe;
        return null;
    }

    /** Read-only list of all recipes, intended for the NEI handler. */
    public static List<Recipe> getRecipes() {
        return Collections.unmodifiableList(RECIPES);
    }

    /** Registers the default recipes. Call it AFTER RecraftedItems.init(), because it uses RecraftedItems.coalCoke. */
    public static void init() {
        // coal -> coke, 90 s (use 100 ticks while testing)
        addRecipe(new ItemStack(Items.coal, 1, 0), new ItemStack(RecraftedItems.coalCoke), 1800); // coal -> coke, 180 s
        addRecipe(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.coal, 1, 1), 900); // log -> charcoal, 90 s

        // Example with an amount and a wildcard metadata:
        // addRecipe(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.coal, 1, 1), 900);
    }
}