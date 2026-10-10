package net.xabyxd.recrafted.compat.nei;

import java.util.LinkedHashMap;
import java.util.Map;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecraftedFurnaceHandler extends FurnaceRecipeHandler {

    // What NEI must show: the actual furnace list, without the "item", and with your guaranteed recipe.
    @SuppressWarnings("unchecked")
    private Map<ItemStack, ItemStack> view() {
        Map<ItemStack, ItemStack> out = new LinkedHashMap<ItemStack, ItemStack>();
        Map<ItemStack, ItemStack> live = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList();

        for (Map.Entry<ItemStack, ItemStack> e : live.entrySet()) {
            if (e.getKey() == null || NEIRecipes.isSmeltingInputHidden(e.getKey())) continue;
            out.put(e.getKey(), e.getValue());
        }

        for (NEIRecipes.SmeltingEntry extra : NEIRecipes.extraSmelting()) {
            boolean present = false;
            for (ItemStack in : out.keySet()) {
                if (NEIServerUtils.areStacksSameTypeCrafting(in, extra.input)) { present = true; break; }
            }
            if (!present) out.put(extra.input.copy(), extra.output.copy());
        }
        return out;
    }

    @Override
    public TemplateRecipeHandler newInstance() { return new RecraftedFurnaceHandler(); }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if ("smelting".equals(outputId)) {
            for (Map.Entry<ItemStack, ItemStack> e : view().entrySet())
                arecipes.add(new SmeltingPair(e.getKey(), e.getValue()));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (Map.Entry<ItemStack, ItemStack> e : view().entrySet())
            if (NEIServerUtils.areStacksSameType(e.getValue(), result))
                arecipes.add(new SmeltingPair(e.getKey(), e.getValue()));
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (Map.Entry<ItemStack, ItemStack> e : view().entrySet()) {
            if (NEIServerUtils.areStacksSameTypeCrafting(e.getKey(), ingredient)) {
                SmeltingPair r = new SmeltingPair(e.getKey(), e.getValue());
                r.setIngredientPermutation(r.getIngredients(), ingredient);
                arecipes.add(r);
            }
        }
    }
}