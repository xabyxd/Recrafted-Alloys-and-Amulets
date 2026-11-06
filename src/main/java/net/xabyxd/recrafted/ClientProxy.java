package net.xabyxd.recrafted;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.xabyxd.recrafted.compat.KeyHandler;
import net.xabyxd.recrafted.config.Config;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (Config.EnableCompatMenu) {
            KeyHandler.init();
        }
        if (Config.EnablePauseMenuElement) {
            MinecraftForge.EVENT_BUS.register(new PauseMenuHandler());
        }
    }
}