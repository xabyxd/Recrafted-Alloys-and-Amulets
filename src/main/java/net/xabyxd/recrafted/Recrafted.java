package net.xabyxd.recrafted;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = Recrafted.MODID,
    version = Recrafted.VERSION,
    name = "Recrafted: Alloys & Amulets",
    dependencies = "required-after:Forge@[10.13.4.1614];"
                + "after:IC2;"
                + "after:Baubles;"
                + "after:Thaumcraft;"
                + "after:NotEnoughItems",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = Recrafted.VERSION
)

public class Recrafted {

    public static final String MODID = "recrafted";
    public static final String MOD_NAME = "Recrafted: Alloys & Amulets";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(
        clientSide = "net.xabyxd.recrafted.ClientProxy",
        serverSide = "net.xabyxd.recrafted.CommonProxy"
    )

    public static CommonProxy proxy;

    @EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}