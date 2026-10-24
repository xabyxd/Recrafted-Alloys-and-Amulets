package net.xabyxd.recrafted.compat.nei;

import java.util.Iterator;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import net.minecraft.item.ItemStack;
import net.xabyxd.recrafted.registers.RecraftedBlocks;
import net.xabyxd.recrafted.utils.LogHelper;

/**
 * Entry point of the NEI integration. NEI finds this class by itself (it looks for IConfigureNEI
 * implementations named NEI*Config), so it is NOT registered anywhere in the mod. It is only loaded
 * on the client and only when NEI is installed.
 */
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

        // Coke oven recipes (recipes tab and usage tab)
        CokeOvenRecipeHandler cokeOvenHandler = new CokeOvenRecipeHandler();
        API.registerRecipeHandler(cokeOvenHandler);
        API.registerUsageHandler(cokeOvenHandler);
        API.addRecipeCatalyst(new ItemStack(RecraftedBlocks.cokeOvenController), cokeOvenHandler.getOverlayIdentifier());
    }

    @Override public String getName() {
        return "Rectafted NEI Integration";
    }

    @Override public String getVersion() {
        return "1.0.0";
    }
}