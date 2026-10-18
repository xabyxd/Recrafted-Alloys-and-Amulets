package net.xabyxd.recrafted.compat.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.xabyxd.recrafted.blocks.machines.CokeOvenController;

public class WailaCompat implements IWailaDataProvider {

    /** Waila refers to this method as reflection (see the IMC record below). */
    public static void callbackRegister(IWailaRegistrar registrar) {
        WailaCompat provider = new WailaCompat();
        registrar.registerStackProvider(provider, CokeOvenController.class);
        registrar.registerBodyProvider(provider, CokeOvenController.class);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        // Metadata 0 = the side with the mouth always faces forward, regardless of which way the block is oriented
        return new ItemStack(accessor.getBlock(), 1, 0);
    }

    @Override
    public List<String> getWailaHead(ItemStack stack, List<String> tip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return tip;
    }

    @Override
    public List<String> getWailaBody(ItemStack stack, List<String> tip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        boolean active = (accessor.getMetadata() & 8) != 0;
        String state = active
            ? EnumChatFormatting.GREEN + StatCollector.translateToLocal("waila.recrafted.active")
            : EnumChatFormatting.RED + StatCollector.translateToLocal("waila.recrafted.inactive");
        tip.add(StatCollector.translateToLocal("waila.recrafted.state") + ": " + state);
        return tip;
    }

    @Override
    public List<String> getWailaTail(ItemStack stack, List<String> tip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return tip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return tag;
    }
}