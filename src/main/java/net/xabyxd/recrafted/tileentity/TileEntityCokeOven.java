package net.xabyxd.recrafted.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.xabyxd.recrafted.blocks.machines.CokeOvenController;
import net.xabyxd.recrafted.registers.recipes.CokeOvenRecipes;

/**
 * Tile entity of the coke oven. Holds the two slots (input and output), cooks the input following
 * {@link CokeOvenRecipes} and pushes the result to the inventory placed in front of the machine.
 *
 * Automation access (hoppers, AE2, pipes) goes through {@link ISidedInventory}:
 * - front face: input and output
 * - bottom face: output only
 * - top face: input only
 * - other faces: nothing
 * The input can never be extracted by automation.
 */
public class TileEntityCokeOven extends TileEntity implements ISidedInventory {

    public static final int SLOT_INPUT = 0, SLOT_OUTPUT = 1;

    private final ItemStack[] slots = new ItemStack[2];
    /** Cooking progress in ticks. Synced to the client by the container. */
    public int progress;

    private static final int[] SLOTS_NONE = new int[0];
    private static final int[] SLOTS_INPUT = { SLOT_INPUT };
    private static final int[] SLOTS_OUTPUT = { SLOT_OUTPUT };
    private static final int[] SLOTS_FRONT = { SLOT_INPUT, SLOT_OUTPUT };

    private static final int PUSH_INTERVAL = 8;  // ticks between attempts to push the output
    private static final int PUSH_AMOUNT = 16;    // maximum items moved per attempt
    private int pushTimer;

    /** Recipe being cooked on the last tick, used to detect recipe changes. Server only, not saved. */
    private CokeOvenRecipes.Recipe currentRecipe;

    /**
     * Finds the recipe that can be cooked right now.
     *
     * @return the recipe, or null if the input is empty, has no recipe, there are not enough items
     *         or the result does not fit in the output slot
     */
    private CokeOvenRecipes.Recipe findRecipe() {
        ItemStack input = slots[SLOT_INPUT];
        CokeOvenRecipes.Recipe recipe = CokeOvenRecipes.getRecipe(input);
        if (recipe == null || input.stackSize < recipe.input.stackSize) return null;

        ItemStack output = slots[SLOT_OUTPUT];
        if (output == null) return recipe;
        if (!output.isItemEqual(recipe.output)) return null;
        int total = output.stackSize + recipe.output.stackSize;
        return (total <= getInventoryStackLimit() && total <= output.getMaxStackSize()) ? recipe : null;
    }

    /** Finishes a craft: adds the result to the output slot and consumes the input. */
    private void smelt(CokeOvenRecipes.Recipe recipe) {
        if (slots[SLOT_OUTPUT] == null) slots[SLOT_OUTPUT] = recipe.output.copy();
        else slots[SLOT_OUTPUT].stackSize += recipe.output.stackSize;
        slots[SLOT_INPUT].stackSize -= recipe.input.stackSize;
        if (slots[SLOT_INPUT].stackSize <= 0) slots[SLOT_INPUT] = null;
    }

    /**
     * Runs every tick on both sides, but all the logic is server only:
     * advances the cooking progress, finishes crafts, updates the front texture (lit/unlit)
     * and periodically pushes the output.
     */
    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        CokeOvenRecipes.Recipe recipe = findRecipe();
        if (recipe == null) progress = 0;
        else if (currentRecipe != null && currentRecipe != recipe) progress = 0; // recipe changed, restart
        currentRecipe = recipe;

        if (recipe != null && ++progress >= recipe.time) {
            progress = 0;
            smelt(recipe);
            markDirty();
        }
        CokeOvenController.setActive(worldObj, xCoord, yCoord, zCoord, progress > 0);

        if (++pushTimer >= PUSH_INTERVAL) {
            pushTimer = 0;
            pushOutput();
        }
    }

    // ---- Auto-push of the output to the inventory in front of the machine ----

    /**
     * Moves part of the output slot into the inventory placed against the front face (chest, AE2 interface...).
     * Items are only removed from the output slot if the target actually accepted them.
     */
    private void pushOutput() {
        ItemStack output = slots[SLOT_OUTPUT];
        if (output == null) return;

        int front = getFrontSide();
        if (front < 2) return;
        ForgeDirection direction = ForgeDirection.getOrientation(front);
        TileEntity neighbor = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
        if (neighbor == null || neighbor == this || !(neighbor instanceof IInventory)) return;

        ItemStack toMove = output.copy();
        toMove.stackSize = Math.min(output.stackSize, PUSH_AMOUNT);
        int before = toMove.stackSize;

        // The face of the neighbor that touches our front is the opposite one
        ItemStack leftover = insertInto((IInventory) neighbor, toMove, direction.getOpposite().ordinal());
        int moved = before - (leftover == null ? 0 : leftover.stackSize);
        if (moved > 0) decrStackSize(SLOT_OUTPUT, moved);
    }

    /**
     * Inserts a stack into an inventory the way a hopper would: respects the accessible slots and the
     * insertion rules of sided inventories, merges into matching stacks and fills empty slots.
     *
     * @param inventory target inventory
     * @param stack     stack to insert (it is modified)
     * @param side      face of the target inventory being used
     * @return what did not fit, or null if everything was inserted
     */
    private static ItemStack insertInto(IInventory inventory, ItemStack stack, int side) {
        int[] accessibleSlots;
        if (inventory instanceof ISidedInventory) {
            accessibleSlots = ((ISidedInventory) inventory).getAccessibleSlotsFromSide(side);
        } else {
            accessibleSlots = new int[inventory.getSizeInventory()];
            for (int index = 0; index < accessibleSlots.length; index++) accessibleSlots[index] = index;
        }

        for (int slot : accessibleSlots) {
            if (stack.stackSize <= 0) return null;
            if (!inventory.isItemValidForSlot(slot, stack)) continue;
            if (inventory instanceof ISidedInventory && !((ISidedInventory) inventory).canInsertItem(slot, stack, side)) continue;

            ItemStack targetStack = inventory.getStackInSlot(slot);
            int stackLimit = Math.min(inventory.getInventoryStackLimit(), stack.getMaxStackSize());
            if (targetStack == null) {
                int amount = Math.min(stack.stackSize, stackLimit);
                ItemStack placed = stack.copy();
                placed.stackSize = amount;
                inventory.setInventorySlotContents(slot, placed);
                stack.stackSize -= amount;
            } else if (targetStack.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(targetStack, stack) && targetStack.stackSize < stackLimit) {
                int amount = Math.min(stack.stackSize, stackLimit - targetStack.stackSize);
                targetStack.stackSize += amount;
                stack.stackSize -= amount;
                inventory.markDirty();
            }
        }
        return stack.stackSize <= 0 ? null : stack;
    }

    /**
     * Progress scaled to a number of pixels, used to draw the arrow in the GUI.
     * Works on both sides: the recipe is taken from the input slot, which is already synced.
     *
     * @param pixels size of the full progress bar
     */
    public int getProgressScaled(int pixels) {
        CokeOvenRecipes.Recipe recipe = CokeOvenRecipes.getRecipe(slots[SLOT_INPUT]);
        return recipe == null ? 0 : progress * pixels / recipe.time;
    }

    // ---- NBT ----

    /** Loads the slot contents and the cooking progress from the saved data. */
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList list = tag.getTagList("Items", 10);
        for (int index = 0; index < list.tagCount(); index++) {
            NBTTagCompound slotTag = list.getCompoundTagAt(index);
            int slot = slotTag.getByte("Slot");
            if (slot >= 0 && slot < slots.length) slots[slot] = ItemStack.loadItemStackFromNBT(slotTag);
        }
        progress = tag.getShort("Progress");
    }

    /** Saves the slot contents and the cooking progress. */
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList list = new NBTTagList();
        for (int index = 0; index < slots.length; index++) {
            if (slots[index] != null) {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) index);
                slots[index].writeToNBT(slotTag);
                list.appendTag(slotTag);
            }
        }
        tag.setTag("Items", list);
        tag.setShort("Progress", (short) progress);
    }

    // ---- IInventory ----
    @Override public int getSizeInventory() { return slots.length; }
    @Override public ItemStack getStackInSlot(int slot) { return slots[slot]; }

    /** Removes up to {@code amount} items from a slot and returns them. */
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (slots[slot] == null) return null;
        ItemStack removed;
        if (slots[slot].stackSize <= amount) { removed = slots[slot]; slots[slot] = null; }
        else { removed = slots[slot].splitStack(amount); if (slots[slot].stackSize == 0) slots[slot] = null; }
        markDirty();
        return removed;
    }

    /** Called when a container closes and wants the slot contents back; empties the slot and returns its stack. */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = slots[slot]; slots[slot] = null; return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        slots[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) stack.stackSize = getInventoryStackLimit();
        markDirty();
    }

    @Override public String getInventoryName() { return "container.recrafted.coke_oven"; }
    @Override public boolean hasCustomInventoryName() { return false; }
    @Override public int getInventoryStackLimit() { return 64; }

    /** The player can use the GUI while the tile still exists and they are within 8 blocks. */
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override public void openInventory() {}
    @Override public void closeInventory() {}

    /** Only the input slot accepts items, and only those that have a recipe. */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == SLOT_INPUT && CokeOvenRecipes.getRecipe(stack) != null;
    }

    // ---- ISidedInventory (hoppers, AE2, pipes) ----
    // side: 0 = bottom, 1 = top, 2-5 = horizontal faces (same number as the orientation metadata)

    /** Front face of the machine: the orientation is stored in bits 0-2 of the metadata (bit 3 is "active"). */
    private int getFrontSide() {
        return worldObj == null ? -1 : worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & 7;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == getFrontSide()) return SLOTS_FRONT;  // front: input and output
        if (side == 0) return SLOTS_OUTPUT;              // bottom: output only
        if (side == 1) return SLOTS_INPUT;               // top: input only
        return SLOTS_NONE;                               // other faces (hidden by the multiblock)
    }

    private boolean isAccessible(int slot, int side) {
        for (int accessibleSlot : getAccessibleSlotsFromSide(side)) if (accessibleSlot == slot) return true;
        return false;
    }

    /** Automation can only insert valid items into the input slot, and only through faces that give access to it. */
    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot == SLOT_INPUT && isAccessible(slot, side) && isItemValidForSlot(slot, stack);
    }

    /** Automation can only extract from the output slot, never the input. */
    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == SLOT_OUTPUT && isAccessible(slot, side);
    }
}