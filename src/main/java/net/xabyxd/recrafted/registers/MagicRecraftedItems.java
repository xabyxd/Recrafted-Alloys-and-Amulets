package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.items.amulets.MinerAmulet;
import net.xabyxd.recrafted.items.amulets.SpeedAmulet;
import net.xabyxd.recrafted.items.amulets.VigorAmulet;
import net.xabyxd.recrafted.utils.LogHelper;

public class MagicRecraftedItems {

    // Item registration
    public static Item vigorAmulet;
    public static Item minerAmulet;
    public static Item speedAmulet;

    public static void init() {
        if (!Compat.BaublesLoaded || !Compat.ThaumcraftLoaded) {
            LogHelper.warn("" + Recrafted.MODID + "] Magic items disabled. Missing: "
                + (!Compat.BaublesLoaded ? "Baubles " : "")
                + (!Compat.ThaumcraftLoaded ? "Thaumcraft" : ""));
            return;
        }
    
        // Amulets builder (PLACEHOLDER)
        vigorAmulet = new VigorAmulet();
        minerAmulet = new MinerAmulet();
        speedAmulet = new SpeedAmulet();

        // Amulets registration (PLACEHOLDER)
        GameRegistry.registerItem(vigorAmulet, "vigor_amulet");
        GameRegistry.registerItem(minerAmulet, "miner_amulet");
        GameRegistry.registerItem(speedAmulet, "speed_amulet");
    }
}