package net.xabyxd.recrafted;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.xabyxd.recrafted.registers.RecraftedItems;

public class RecraftedCreativeTab extends CreativeTabs {

    public RecraftedCreativeTab() {
        super("recrafted");
    }

    @Override
    public Item getTabIconItem() {
        return RecraftedItems.copperPickaxe;
    }
}