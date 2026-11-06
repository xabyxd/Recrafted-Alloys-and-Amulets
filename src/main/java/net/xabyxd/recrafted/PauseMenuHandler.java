package net.xabyxd.recrafted;

import java.awt.Desktop;
import java.net.URI;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;

@SideOnly(Side.CLIENT)
public class PauseMenuHandler {

    private static final int BTN_BUG  = 9001;
    private static final int BTN_WIKI = 9002;
    private static final ResourceLocation LOGO = new ResourceLocation(Recrafted.MODID, "textures/gui/recrafted-icon.png");

    /** Centralized URLs: make changes only here. */
    private static final String URL_BUG  = "https://github.com/xabyxd/Recrafted-Alloys-and-Amulets/issues/";
    private static final String URL_WIKI = "https://xabyserver.ddns.net/Recrafted/wiki/";

    /**
     * A custom subclass to distinguish OUR buttons from those of other mods,
     * even if the IDs happen to match (prevents collisions).
     */
    private static final class RecraftedButton extends GuiButton {
        RecraftedButton(int id, int x, int y, int w, int h, String label) {
            super(id, x, y, w, h, label);
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void onInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!(event.gui instanceof GuiIngameMenu)) return;

        int x = event.gui.width / 2 - 100 - 104;
        int y = event.gui.height / 4 + 24 - 16;

        event.buttonList.add(new RecraftedButton(BTN_BUG,  x,      y,      98, 20, "Report a Bug"));
        event.buttonList.add(new RecraftedButton(BTN_WIKI, x, y + 24, 98, 20, "Open the Wiki"));
    }

    @SubscribeEvent
    public void onAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (!(event.gui instanceof GuiIngameMenu)) return;
        // We only react to buttons that we ourselves have created.
        if (!(event.button instanceof RecraftedButton)) return;

        switch (event.button.id) {
            case BTN_BUG:  openAsync(URL_BUG);  break;
            case BTN_WIKI: openAsync(URL_WIKI); break;
            default: break;
        }
    }

    @SubscribeEvent
    public void onDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (!(event.gui instanceof GuiIngameMenu)) return;

        int size = 64;
        int x = event.gui.width / 2 - 100 - 104 + (98 - size) / 2;
        int y = event.gui.height / 4 + 24 - 16 - size - 4;

        // If the logo does not fit on the screen (small windows), we do not draw anything.
        if (x < 0 || y < 0 || x + size > event.gui.width || y + size > event.gui.height) return;

        Minecraft.getMinecraft().getTextureManager().bindTexture(LOGO);
        GL11.glColor4f(1F, 1F, 1F, 1F);

        boolean blendWasOn = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

        Gui.func_146110_a(x, y, 0, 0, size, size, size, size);

        // We restore the state so as not to break other handlers for the same event.
        if (!blendWasOn) GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1F, 1F, 1F, 1F);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Opens the browser in a separate thread so as not to block the game's rendering.
     * If the system does not support Desktop.browse, it will not crash.
     */
    private static void openAsync(final String url) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI(url));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, "recrafted-url-opener");
        thread.setDaemon(true);
        thread.start();
    }
}