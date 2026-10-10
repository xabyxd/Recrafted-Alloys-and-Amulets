package net.xabyxd.recrafted.compat.nei;

import java.util.Iterator;

import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.utils.LogHelper;

public class NEIRecraftedConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        NEIRecipes.defineAll();

        int removed = 0;
        for (Iterator<ICraftingHandler> it = GuiCraftingRecipe.craftinghandlers.iterator(); it.hasNext();) {
            if (it.next().getClass() == FurnaceRecipeHandler.class) { it.remove(); removed++; }
        }
        for (Iterator<IUsageHandler> it = GuiUsageRecipe.usagehandlers.iterator(); it.hasNext();) {
            if (it.next().getClass() == FurnaceRecipeHandler.class) { it.remove(); removed++; }
        }

        GuiCraftingRecipe.craftinghandlers.add(new RecraftedFurnaceHandler());
        GuiUsageRecipe.usagehandlers.add(new RecraftedFurnaceHandler());
        LogHelper.info("NEI: furnace handler replaced (removed {} originals)", removed);
    }

    @Override public String getName() { return Recrafted.MOD_NAME; }
    @Override public String getVersion() { return Recrafted.VERSION; }
}