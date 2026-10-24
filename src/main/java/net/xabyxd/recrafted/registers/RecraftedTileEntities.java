package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.xabyxd.recrafted.tileentity.TileEntityCokeOven;

public class RecraftedTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCokeOven.class, "recrafted:coke_oven");
    }
}