package net.xabyxd.recrafted.items.tools;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.ItemAxe;
import net.xabyxd.recrafted.Recrafted;

public class CopperAxe extends ItemAxe {
    
    public CopperAxe(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_axe");
        setTextureName(Recrafted.MODID + ":copper_axe");
        setCreativeTab(RecraftedTAB);
    }
}
