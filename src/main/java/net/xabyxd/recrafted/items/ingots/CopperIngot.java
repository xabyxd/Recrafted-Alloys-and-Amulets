package net.xabyxd.recrafted.items.ingots;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.Recrafted;

public class CopperIngot extends Item {
    
    public CopperIngot() {
        OreDictionary.registerOre("ingotCopper", new ItemStack(this));
        setUnlocalizedName("copper_ingot");
        setTextureName(Recrafted.MODID + ":copper_ingot");
        setCreativeTab(RecraftedTAB);
    }
}
