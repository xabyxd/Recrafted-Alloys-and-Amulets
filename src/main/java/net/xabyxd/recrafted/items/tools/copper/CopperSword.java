package net.xabyxd.recrafted.items.tools.copper;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class CopperSword extends ItemSword {
    
    public CopperSword(ToolMaterial material) {
        super(material);
        setUnlocalizedName("copper_sword");
        setTextureName(Recrafted.MODID + ":copper_sword");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.copper_sword"));
    }
}