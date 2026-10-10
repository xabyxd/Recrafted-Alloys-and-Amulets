package net.xabyxd.recrafted.compat;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class KeyHandler {

    public static final KeyBinding OPEN_COMPAT =
        new KeyBinding("Recrafted: compat status", Keyboard.KEY_F10, "Recrafted");

    public static void init() {
        ClientRegistry.registerKeyBinding(OPEN_COMPAT);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (OPEN_COMPAT.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiCompatStatus());
        }
    }
}