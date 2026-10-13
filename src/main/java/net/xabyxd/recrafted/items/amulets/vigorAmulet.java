package net.xabyxd.recrafted.items.amulets;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class vigorAmulet extends Item {
    
    public vigorAmulet() {
        setUnlocalizedName("vigor_amulet");
        setTextureName(Recrafted.MODID + ":vigor_amulet");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.vigor_amulet"));
    }
}