package net.xabyxd.recrafted.items.bricks;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class CokeOvenBrick extends Item {
    
    public CokeOvenBrick() {
        setUnlocalizedName("coke_oven_brick");
        setTextureName(Recrafted.MODID + ":coke_oven_brick");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.coke_oven_brick"));
    }
}