package net.xabyxd.recrafted.items.tools.wood;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.xabyxd.recrafted.Recrafted;

public class BrickMold extends Item {
    
    public BrickMold() {
        setUnlocalizedName("brick_mold");
        setTextureName(Recrafted.MODID + ":brick_mold");
        setCreativeTab(RecraftedTAB);
        setMaxStackSize(1);
        setMaxDamage(64);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack damaged = stack.copy();
        damaged.stackSize = 1;
        damaged.setItemDamage(stack.getItemDamage() + 1);
        return damaged;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    @Override
    @SuppressWarnings ({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(StatCollector.translateToLocal("tooltip.recrafted.brick_mold"));
    }
}
