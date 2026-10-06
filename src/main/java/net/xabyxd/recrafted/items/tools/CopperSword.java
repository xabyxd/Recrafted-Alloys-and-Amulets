package net.xabyxd.recrafted.items.tools;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.item.ItemSword;
import net.xabyxd.recrafted.Recrafted;

public class CopperSword extends ItemSword {
    
    public CopperSword(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_sword");
        setTextureName(Recrafted.MODID + ":copper_sword");
        setCreativeTab(RecraftedTAB);
    }
}
