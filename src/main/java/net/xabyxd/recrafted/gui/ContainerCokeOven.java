package net.xabyxd.recrafted.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.xabyxd.recrafted.registers.recipes.CokeOvenRecipes;
import net.xabyxd.recrafted.tileentity.TileEntityCokeOven;

/**
 * Server-side container of the coke oven: defines the slot layout, keeps the client's progress bar
 * in sync and handles shift-click.
 *
 * Slot indices: 0 = input, 1 = output, 2-28 = player inventory, 29-37 = hotbar.
 */
public class ContainerCokeOven extends Container {

    private final TileEntityCokeOven tile;
    /** Last progress value sent to the clients, used to send updates only when it changes. */
    private int lastProgress = -1;

    public ContainerCokeOven(InventoryPlayer playerInventory, TileEntityCokeOven tile) {
        this.tile = tile;

        // Input: only accepts items that have a recipe
        addSlotToContainer(new Slot(tile, TileEntityCokeOven.SLOT_INPUT, 56, 35) {
            @Override public boolean isItemValid(ItemStack stack) { return tile.isItemValidForSlot(TileEntityCokeOven.SLOT_INPUT, stack); }
        });
        // Output: items can only be taken out, never placed in
        addSlotToContainer(new Slot(tile, TileEntityCokeOven.SLOT_OUTPUT, 116, 35) {
            @Override public boolean isItemValid(ItemStack stack) { return false; }
        });

        // Player inventory (3 rows of 9) and hotbar
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 9; column++)
                addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
        for (int column = 0; column < 9; column++)
            addSlotToContainer(new Slot(playerInventory, column, 8 + column * 18, 142));
    }

    /** Sends the current progress to a player as soon as they open the GUI. */
    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, tile.progress);
    }

    /** Runs every tick on the server: sends the progress to all viewers when it has changed. */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (lastProgress != tile.progress) {
            for (Object crafter : crafters) ((ICrafting) crafter).sendProgressBarUpdate(this, 0, tile.progress);
            lastProgress = tile.progress;
        }
    }

    /** Client side: receives the values sent by {@link #detectAndSendChanges()}. Bar 0 is the cooking progress. */
    @Override
    public void updateProgressBar(int barId, int value) {
        if (barId == 0) tile.progress = value;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) { return tile.isUseableByPlayer(player); }

    /**
     * Shift-click behavior:
     * - from the oven slots: moves the stack to the player inventory
     * - from the player: items with a recipe go to the input slot, the rest moves between inventory and hotbar
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack copy = null;
        Slot slot = (Slot) inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            copy = stack.copy();

            if (index < 2) {
                if (!mergeItemStack(stack, 2, 38, true)) return null;
                slot.onSlotChange(stack, copy);
            } else if (CokeOvenRecipes.getRecipe(stack) != null) {
                if (!mergeItemStack(stack, 0, 1, false)) return null;
            } else if (index < 29) {
                if (!mergeItemStack(stack, 29, 38, false)) return null;
            } else if (!mergeItemStack(stack, 2, 29, false)) {
                return null;
            }

            if (stack.stackSize == 0) slot.putStack(null); else slot.onSlotChanged();
            if (stack.stackSize == copy.stackSize) return null;
            slot.onPickupFromSlot(player, stack);
        }
        return copy;
    }
}