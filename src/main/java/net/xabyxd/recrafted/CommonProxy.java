package net.xabyxd.recrafted;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.xabyxd.recrafted.config.Config;
import net.xabyxd.recrafted.registers.ModItems;
import net.xabyxd.recrafted.registers.RecipesRegister;
import net.xabyxd.recrafted.utils.LogHelper;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration();
        ModItems.init();
        LogHelper.info(Recrafted.MOD_NAME + " version " + Recrafted.VERSION + " loaded");
        LogHelper.info("Pre-initialization completed");
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        RecipesRegister.RecipesInit();
        LogHelper.info("Recipes initialization completed");
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post-initialization completed");
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        LogHelper.info("Server starting");
    }
}