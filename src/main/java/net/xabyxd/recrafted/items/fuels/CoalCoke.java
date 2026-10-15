package net.xabyxd.recrafted.items.fuels;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class CoalCoke extends Item {
    
    public CoalCoke() {
        setUnlocalizedName("coal_coke");
        setTextureName(Recrafted.MODID + ":coal_coke");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.coal_coke"));
    }
}