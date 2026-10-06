package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.items.ingots.CopperIngot;
import net.xabyxd.recrafted.items.tools.CopperAxe;
import net.xabyxd.recrafted.items.tools.CopperPickaxe;

public class ModItems {

    public static ToolMaterial COPPER;
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperIngot;

    public static void init() {
        // Tool material builder
        COPPER = EnumHelper.addToolMaterial("COPPER", 2, 190, 5.0F, 1.5F, 8);

        // Item builder
        copperPickaxe = new CopperPickaxe(COPPER);
        copperAxe = new CopperAxe(COPPER);
        copperIngot = new CopperIngot();

        // Item registration
        GameRegistry.registerItem(copperPickaxe, "copper_pickaxe");
        GameRegistry.registerItem(copperAxe, "copper_axe");
        GameRegistry.registerItem(copperIngot, "copper_ingot");

        // Ore dictionary registration
        OreDictionary.registerOre("ingotCopper", copperIngot);
    }
}