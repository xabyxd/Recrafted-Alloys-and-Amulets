package net.xabyxd.recrafted.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.xabyxd.recrafted.Recrafted;

public class CopperPickaxe extends ItemPickaxe {
    
    public CopperPickaxe(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_pickaxe");
        setTextureName(Recrafted.MODID + ":copper_pickaxe");
        setCreativeTab(CreativeTabs.tabTools);
    }
}
