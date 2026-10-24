package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.gui.GuiHandler;

public class RecraftedGuis {

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Recrafted.instance, new GuiHandler());
    }
}