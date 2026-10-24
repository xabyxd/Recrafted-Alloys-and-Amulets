package net.xabyxd.recrafted.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.tileentity.TileEntityCokeOven;

/**
 * Client-side screen of the coke oven.
 * Draws the brick-themed background and the progress arrow; all the logic lives in
 * {@link ContainerCokeOven} and {@link TileEntityCokeOven}.
 */
public class GuiCokeOven extends GuiContainer {

    /** GUI texture (256x256): background at (0,0), filled progress arrow at (176,14). */
    private static final ResourceLocation COKE_OVEN_GUI = new ResourceLocation(Recrafted.MODID, "textures/gui/coke_oven.png");
    private final TileEntityCokeOven tile;

    /**
     * @param playerInventory inventory of the player that opened the GUI
     * @param tile            coke oven being displayed (client-side copy, progress is synced by the container)
     */
    public GuiCokeOven(InventoryPlayer playerInventory, TileEntityCokeOven tile) {
        super(new ContainerCokeOven(playerInventory, tile));
        this.tile = tile;
    }

    /** Draws the texts on top of the background: the centered title and the "Inventory" label. */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format(tile.getInventoryName());
        fontRendererObj.drawString(title, xSize / 2 - fontRendererObj.getStringWidth(title) / 2, 6, 0xE8D8C8);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 0x404040);
    }

    /** Draws the background texture and the part of the progress arrow that is already filled. */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(COKE_OVEN_GUI);
        int guiX = (width - xSize) / 2, guiY = (height - ySize) / 2;
        drawTexturedModalRect(guiX, guiY, 0, 0, xSize, ySize);
        // Arrow: same position and size as the vanilla furnace (79,34 -> 24x17), filled sprite at u=176, v=14
        drawTexturedModalRect(guiX + 79, guiY + 34, 176, 14, tile.getProgressScaled(24) + 1, 17);
    }
}