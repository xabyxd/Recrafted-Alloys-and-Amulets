package net.xabyxd.recrafted.compat;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

// ONLY FOR DEVELOPMENT, REMOVE BEFORE RELEASE (or make it a config option)
@SideOnly(Side.CLIENT)
public class GuiCompatStatus extends GuiScreen {

    private static final int GREEN = 0x55FF55;
    private static final int RED = 0xFF5555;
    private static final int WHITE = 0xFFFFFF;
    private static final int GRAY = 0xAAAAAA;
    private static final int LINE_HEIGHT = 10;

    private static class Entry {
        final String name;
        final String id;
        final boolean loaded;

        Entry(String name, String id, boolean loaded) {
            this.name = name;
            this.id = id;
            this.loaded = loaded;
        }
    }

    private final List<Entry> integrations = new ArrayList<Entry>();
    private final List<String> modLines = new ArrayList<String>();
    private int scroll = 0;

    public GuiCompatStatus() {
        integrations.add(new Entry("NEI", "NotEnoughItems", Compat.NEILoaded));
        integrations.add(new Entry("IC2", "IC2", Compat.IC2Loaded));
        integrations.add(new Entry("Baubles", "Baubles", Compat.BaublesLoaded));
        integrations.add(new Entry("Thaumcraft", "Thaumcraft", Compat.ThaumcraftLoaded));

        for (ModContainer mod : Loader.instance().getModList()) {
            modLines.add(mod.getModId() + "  |  " + mod.getName() + "  |  " + mod.getVersion());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 50, height - 26, 100, 20, "Close"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) mc.displayGuiScreen(null);
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int wheel = Mouse.getEventDWheel();
        if (wheel != 0) scroll -= Integer.signum(wheel) * 3;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Recrafted - Compat status", width / 2, 10, WHITE);

        // Integrations detected by Recrafted
        int y = 28;
        for (Entry e : integrations) {
            String status = e.loaded ? "LOADED" : "NOT FOUND";
            drawString(fontRendererObj, e.name + " (" + e.id + ")", 20, y, WHITE);
            drawString(fontRendererObj, status, width - 100, y, e.loaded ? GREEN : RED);
            y += LINE_HEIGHT + 2;
        }

        // Full mod list (scrollable)
        y += 6;
        drawString(fontRendererObj, "Loaded mods (" + modLines.size() + "):", 20, y, WHITE);
        y += LINE_HEIGHT + 4;

        int top = y;
        int bottom = height - 34;
        int visible = Math.max(1, (bottom - top) / LINE_HEIGHT);
        int maxScroll = Math.max(0, modLines.size() - visible);
        scroll = Math.max(0, Math.min(scroll, maxScroll));

        for (int i = 0; i < visible && i + scroll < modLines.size(); i++) {
            drawString(fontRendererObj, modLines.get(i + scroll), 20, top + i * LINE_HEIGHT, GRAY);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}