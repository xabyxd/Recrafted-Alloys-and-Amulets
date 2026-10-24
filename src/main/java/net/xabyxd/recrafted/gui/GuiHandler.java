package net.xabyxd.recrafted.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xabyxd.recrafted.tileentity.TileEntityCokeOven;

/**
 * Creates the container (server side) and the screen (client side) for every GUI of the mod.
 * It is registered once through {@code NetworkRegistry.registerGuiHandler} and is called by Forge
 * when {@code player.openGui(...)} is used. The GUI id selects which GUI is opened.
 */
public class GuiHandler implements IGuiHandler {

    /** GUI id of the coke oven. */
    public static final int COKE_OVEN = 0;

    /** Server side: returns the container that holds the slots and syncs the data. */
    @Override
    public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int blockX, int blockY, int blockZ) {
        TileEntity tileEntity = world.getTileEntity(blockX, blockY, blockZ);
        if (guiId == COKE_OVEN && tileEntity instanceof TileEntityCokeOven)
            return new ContainerCokeOven(player.inventory, (TileEntityCokeOven) tileEntity);
        return null;
    }

    /** Client side: returns the screen that draws the GUI. */
    @Override
    public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int blockX, int blockY, int blockZ) {
        TileEntity tileEntity = world.getTileEntity(blockX, blockY, blockZ);
        if (guiId == COKE_OVEN && tileEntity instanceof TileEntityCokeOven)
            return new GuiCokeOven(player.inventory, (TileEntityCokeOven) tileEntity);
        return null;
    }
}