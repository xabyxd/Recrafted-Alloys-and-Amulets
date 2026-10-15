package net.xabyxd.recrafted.items.nuggets;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class IronNugget extends Item {
    
    public IronNugget() {
        setUnlocalizedName("iron_nugget");
        setTextureName(Recrafted.MODID + ":iron_nugget");
        setCreativeTab(RecraftedTAB);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.iron_nugget"));
    }
}