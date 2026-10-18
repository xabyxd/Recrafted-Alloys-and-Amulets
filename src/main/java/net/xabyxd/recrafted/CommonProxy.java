package net.xabyxd.recrafted;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.config.Config;
import net.xabyxd.recrafted.registers.MagicRecraftedItems;
import net.xabyxd.recrafted.registers.RecipesRegister;
import net.xabyxd.recrafted.registers.RecipesRemover;
import net.xabyxd.recrafted.registers.RecraftedBlocks;
import net.xabyxd.recrafted.registers.RecraftedFuelHandler;
import net.xabyxd.recrafted.registers.RecraftedItems;
import net.xabyxd.recrafted.utils.LogHelper;
import net.xabyxd.recrafted.world.OreGenerator;

public class CommonProxy {

    // Creative tab
    public static final CreativeTabs RecraftedTAB = new RecraftedCreativeTab();

    // preInit: config, items, blocks and their registration.
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info(Recrafted.MOD_NAME + " version " + Recrafted.VERSION + " loading");

        Config.synchronizeConfiguration();
        Compat.init();

        RecraftedItems.init();
        RecraftedBlocks.init();
        MagicRecraftedItems.init();

        LogHelper.info("Pre-initialization completed");
    }

    // init: recipes, fuels, world generation, inter-mod communication.
    public void init(FMLInitializationEvent event) {
        registerFuels();
        registerWailaCompat();

        RecipesRegister.RecipesInit();
        LogHelper.info("Recipes initialization completed");

        GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
        LogHelper.info("World generation initialized");
    }

    // postInit: interaction with other mods.
    public void postInit(FMLPostInitializationEvent event) {
        RecipesRemover.removeVanillaRecipes();
        LogHelper.info("Post-initialization completed");
    }

    public void serverStarting(FMLServerStartingEvent event) {
        LogHelper.info("Server starting");
    }

    private void registerFuels() {
        GameRegistry.registerFuelHandler(
            new RecraftedFuelHandler(
                RecraftedItems.coalCoke,
                Item.getItemFromBlock(RecraftedBlocks.coalCokeBlock)
            )
        );
        LogHelper.info("Fuel handler registered");
    }

    private void registerWailaCompat() {
        if (!Loader.isModLoaded("Waila")) return;

        FMLInterModComms.sendMessage(
            "Waila",
            "register",
            "net.xabyxd.recrafted.compat.waila.WailaCompat.callbackRegister"
        );
        LogHelper.info("Waila compatibility initialized");
    }
}