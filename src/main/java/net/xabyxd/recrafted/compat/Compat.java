package net.xabyxd.recrafted.compat;

import cpw.mods.fml.common.Loader;
import net.xabyxd.recrafted.utils.LogHelper;

public final class Compat {

    // Mod IDs
    private static final String NEI_ID = "NotEnoughItems";
    private static final String IC2_ID = "IC2";
    private static final String BAUBLES_ID = "Baubles";
    private static final String THAUMCRAFT_ID = "Thaumcraft";

    public static boolean NEILoaded = false;
    public static boolean IC2Loaded = false;
    public static boolean BaublesLoaded = false;
    public static boolean ThaumcraftLoaded = false;

    private Compat() {}

    public static void init() {
        // Todo Think about what can be integrated with NEI
        NEILoaded = detect(NEI_ID, "NEI found.");

        // Todo Think about what more to integrate with IC2
        IC2Loaded = detect(IC2_ID, "IC2 found. Recrafted ore generation disabled.");

        // Todo Think about how to create amulets with Baubles and integrate with it
        BaublesLoaded = detect(BAUBLES_ID, "Baubles found. Recrafted amulet slots and amulets enabled.");

        // Todo Think about aspects and stuff to integrate with Thaumcraft
        ThaumcraftLoaded = detect(THAUMCRAFT_ID, "Thaumcraft found.");
    }

    private static boolean detect(String modId, String foundMessage) {
        boolean loaded = Loader.isModLoaded(modId);
        if (loaded) {
            LogHelper.info(foundMessage);
        }
        return loaded;
    }
}