package net.xabyxd.recrafted.items.tools;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.ItemPickaxe;
import net.xabyxd.recrafted.Recrafted;

public class CopperPickaxe extends ItemPickaxe {
    
    public CopperPickaxe(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_pickaxe");
        setTextureName(Recrafted.MODID + ":copper_pickaxe");
        setCreativeTab(RecraftedTAB);
    }
}
