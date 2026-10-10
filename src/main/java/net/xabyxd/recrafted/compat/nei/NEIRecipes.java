package net.xabyxd.recrafted.compat.nei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.xabyxd.recrafted.registers.ModItems;

/** Single place to declare which furnace recipes NEI hides and which ones it must show. No NEI imports on purpose. */
public final class NEIRecipes {

    public static final class SmeltingEntry {
        public final ItemStack input;
        public final ItemStack output;

        SmeltingEntry(ItemStack input, ItemStack output) {
            this.input = input;
            this.output = output;
        }
    }

    private static final List<Item> HIDDEN_SMELTING_INPUTS = new ArrayList<Item>();
    private static final List<Item> HIDDEN_CRAFTING_OUTPUTS = new ArrayList<Item>();
    private static final List<SmeltingEntry> EXTRA_SMELTING = new ArrayList<SmeltingEntry>();
    private static boolean defined = false;

    private NEIRecipes() {}

    // ---- API: use these inside defineAll() ----

    /** Hides every furnace recipe whose input is this item (any damage). */
    public static void hideSmeltingInput(Item item) {
        HIDDEN_SMELTING_INPUTS.add(item);
    }

    /** Removes every crafting table recipe whose result is this item (any damage). */
    public static void hideCraftingOutput(Item item) {
        HIDDEN_CRAFTING_OUTPUTS.add(item);
    }

    /** Makes NEI show input -> output in the furnace tab if the live furnace list doesn't already have it. */
    public static void addSmelting(Item input, ItemStack output) {
        addSmelting(new ItemStack(input), output);
    }

    public static void addSmelting(ItemStack input, ItemStack output) {
        EXTRA_SMELTING.add(new SmeltingEntry(input.copy(), output.copy()));
    }

    // ---- Declarations (edit here) ----

    public static synchronized void defineAll() {
        if (defined) return;
        defined = true;

        // Clay ball no longer smelts into a brick
        hideSmeltingInput(Items.clay_ball);

        // Unfired clay brick -> brick
        addSmelting(ModItems.unfiredClayBrick, new ItemStack(Items.brick));

        // Crafting table: vanilla stone tools are no longer craftable (TESTING)
        hideCraftingOutput(Items.stone_pickaxe);
        hideCraftingOutput(Items.stone_axe);
        hideCraftingOutput(Items.stone_hoe);
        hideCraftingOutput(Items.stone_shovel);
        hideCraftingOutput(Items.stone_sword);

        // More examples:
        // hideSmeltingInput(Items.porkchop);
        // addSmelting(ModBlocks.someBlock_asItem, new ItemStack(ModItems.copperIngot));
    }

    // ---- Queries ----

    public static boolean isSmeltingInputHidden(ItemStack input) {
        return input != null && HIDDEN_SMELTING_INPUTS.contains(input.getItem());
    }

    public static boolean isCraftingOutputHidden(ItemStack output) {
        return output != null && HIDDEN_CRAFTING_OUTPUTS.contains(output.getItem());
    }

    public static List<SmeltingEntry> extraSmelting() {
        return Collections.unmodifiableList(EXTRA_SMELTING);
    }
}