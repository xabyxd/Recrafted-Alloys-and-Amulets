package net.xabyxd.recrafted.items.tools.copper;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class CopperHoe extends ItemHoe {
    
    public CopperHoe(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_hoe");
        setTextureName(Recrafted.MODID + ":copper_hoe");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.copper_hoe"));
    }
}