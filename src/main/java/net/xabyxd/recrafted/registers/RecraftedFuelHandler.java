package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecraftedFuelHandler implements IFuelHandler {

    private static final int COAL_BURN_TIME = 1600;
    private static final int COKE_BURN_TIME = COAL_BURN_TIME * 8;        // 12800
    private static final int COKE_BLOCK_BURN_TIME = COKE_BURN_TIME * 9;  // 115200

    private final Item coalCoke;
    private final Item coalCokeBlock;

    public RecraftedFuelHandler(Item coalCoke, Item coalCokeBlock) {
        this.coalCoke = coalCoke;
        this.coalCokeBlock = coalCokeBlock;
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel == null) return 0;

        Item item = fuel.getItem();
        if (item == coalCoke) return COKE_BURN_TIME;
        if (item == coalCokeBlock) return COKE_BLOCK_BURN_TIME;

        return 0;
    }
}