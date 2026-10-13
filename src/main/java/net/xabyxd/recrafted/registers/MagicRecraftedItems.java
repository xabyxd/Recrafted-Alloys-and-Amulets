package net.xabyxd.recrafted.registers;

import net.minecraft.item.Item;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.utils.LogHelper;

public class MagicRecraftedItems {

    // Item registration
    public static Item vigorAmulet;
    public static Item minerAmulet;

    public static void init() {
        if (!Compat.BaublesLoaded || !Compat.ThaumcraftLoaded) {
            LogHelper.warn("Magic items are dissabled. Thaumcraft and Baubles are missing!!");
            return;
        }
    
        // Amulets builder (PLACEHOLDER)
        // vigorAmulet = new VigorAmulet();
        // minerAmulet = new MinerAmulet();

        
        // Amulets registration (PLACEHOLDER)
        // GameRegistry.registerItem(vigorAmulet, "vigor_amulet");
        // GameRegistry.registerItem(minerAmulet, "miner_amulet");
    }
}