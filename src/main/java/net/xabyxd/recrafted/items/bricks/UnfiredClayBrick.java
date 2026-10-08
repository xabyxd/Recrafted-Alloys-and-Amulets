package net.xabyxd.recrafted.items.bricks;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class UnfiredClayBrick extends Item {
    
    public UnfiredClayBrick() {
        setUnlocalizedName("unfired_clay_brick");
        setTextureName(Recrafted.MODID + ":unfired_clay_brick");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.unfired_clay_brick"));
    }
}