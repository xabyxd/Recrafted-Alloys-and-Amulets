package net.xabyxd.recrafted.items.ingots;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.Item;
import net.xabyxd.recrafted.Recrafted;

public class CopperIngot extends Item {
    
    public CopperIngot() {
        setUnlocalizedName("copper_ingot");
        setTextureName(Recrafted.MODID + ":copper_ingot");
        setCreativeTab(RecraftedTAB);
    }
}
