package net.xabyxd.recrafted.items.tools;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.ItemHoe;
import net.xabyxd.recrafted.Recrafted;

public class CopperHoe extends ItemHoe {
    
    public CopperHoe(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_hoe");
        setTextureName(Recrafted.MODID + ":copper_hoe");
        setCreativeTab(RecraftedTAB);
    }
}
