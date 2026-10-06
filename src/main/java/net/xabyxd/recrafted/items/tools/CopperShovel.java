package net.xabyxd.recrafted.items.tools;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.ItemSpade;
import net.xabyxd.recrafted.Recrafted;

public class CopperShovel extends ItemSpade {
    
    public CopperShovel(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_shovel");
        setTextureName(Recrafted.MODID + ":copper_shovel");
        setCreativeTab(RecraftedTAB);
    }
}
