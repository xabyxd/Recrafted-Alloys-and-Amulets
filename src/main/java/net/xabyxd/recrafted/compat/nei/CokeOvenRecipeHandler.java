package net.xabyxd.recrafted.compat.nei;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.registers.recipes.CokeOvenRecipes;

/**
 * NEI handler that shows the coke oven recipes (both "recipes" and "usage" views).
 * It reads the recipes from {@link CokeOvenRecipes}, so there is nothing to keep in sync.
 *
 * Layout: NEI draws the region (5,11) - (171,76) of the GUI texture, so every coordinate used here is
 * the coordinate in coke_oven.png minus (5,11).
 */
public class CokeOvenRecipeHandler extends TemplateRecipeHandler {

    /** Id used by the transfer rectangle (click on the arrow = show all coke oven recipes). */
    private static final String OVERLAY_ID = "recrafted.coke_oven";

    /** A recipe prepared for display: positioned input and output stacks plus the cooking time. */
    public class CachedCokeOvenRecipe extends CachedRecipe {

        private final PositionedStack input;
        private final PositionedStack output;
        private final int time;

        public CachedCokeOvenRecipe(CokeOvenRecipes.Recipe recipe) {
            // Slots of the GUI texture: input item at (56,35), output item at (116,35)
            this.input = new PositionedStack(recipe.input, 51, 24);
            this.output = new PositionedStack(recipe.output, 111, 24);
            this.time = recipe.time;
        }

        /** Cycles through the possible items when the recipe uses a wildcard metadata (e.g. all log types). */
        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, Arrays.asList(input));
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }
    }

    @Override
    public String getRecipeName() {
        return I18n.format("nei.recrafted.coke_oven");
    }

    @Override
    public String getGuiTexture() {
        return Recrafted.MODID + ":textures/gui/coke_oven.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return OVERLAY_ID;
    }

    public String getModId() {
        return Recrafted.MODID;
    }

    /** Clicking the arrow opens the list with all the coke oven recipes. */
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 17), OVERLAY_ID));
    }

    /** Draws the animated progress arrow and the cooking time. */
    @Override
    public void drawExtras(int recipeIndex) {
        CachedCokeOvenRecipe recipe = (CachedCokeOvenRecipe) arecipes.get(recipeIndex);
        // Filled arrow sprite at (176,14) in the texture, animation loops every 48 ticks
        drawProgressBar(74, 23, 176, 14, 24, 17, 48, 0);
        GuiDraw.drawStringC(I18n.format("nei.recrafted.coke_oven.time", recipe.time / 20), 86, 49, 0xE8D8C8, true);
    }

    /** "Recipes" view of the transfer rectangle: shows every recipe. Anything else is handled by NEI. */
    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(OVERLAY_ID)) {
            for (CokeOvenRecipes.Recipe recipe : CokeOvenRecipes.getRecipes()) arecipes.add(new CachedCokeOvenRecipe(recipe));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    /** "Recipes" view of an item: recipes that produce it. */
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (CokeOvenRecipes.Recipe recipe : CokeOvenRecipes.getRecipes()) {
            if (NEIServerUtils.areStacksSameType(recipe.output, result)) arecipes.add(new CachedCokeOvenRecipe(recipe));
        }
    }

    /** "Usage" view of an item: recipes that use it as input. */
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (CokeOvenRecipes.Recipe recipe : CokeOvenRecipes.getRecipes()) {
            if (recipe.matches(ingredient)) arecipes.add(new CachedCokeOvenRecipe(recipe));
        }
    }
}