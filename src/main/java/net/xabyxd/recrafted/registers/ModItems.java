package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.items.ingots.CopperIngot;
import net.xabyxd.recrafted.items.tools.copper.CopperAxe;
import net.xabyxd.recrafted.items.tools.copper.CopperHoe;
import net.xabyxd.recrafted.items.tools.copper.CopperPickaxe;
import net.xabyxd.recrafted.items.tools.copper.CopperShovel;
import net.xabyxd.recrafted.items.tools.copper.CopperSword;
import net.xabyxd.recrafted.items.tools.wood.BrickMold;

public class ModItems {

    public static ToolMaterial COPPER;
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperSword;
    public static Item copperShovel;
    public static Item copperHoe;
    public static Item copperIngot;
    public static Item brickMold;

    public static void init() {
        // Tool material builder
        COPPER = EnumHelper.addToolMaterial("COPPER", 2, 190, 5.0F, 1.5F, 8);

        // Item builder
        copperPickaxe = new CopperPickaxe(COPPER);
        copperAxe = new CopperAxe(COPPER);
        copperSword = new CopperSword(COPPER);
        copperShovel = new CopperShovel(COPPER);
        copperHoe = new CopperHoe(COPPER);
        copperIngot = new CopperIngot();
        brickMold = new BrickMold();

        // Item registration
        GameRegistry.registerItem(copperPickaxe, "copper_pickaxe");
        GameRegistry.registerItem(copperAxe, "copper_axe");
        GameRegistry.registerItem(copperSword, "copper_sword");
        GameRegistry.registerItem(copperShovel, "copper_shovel");
        GameRegistry.registerItem(copperHoe, "copper_hoe");
        GameRegistry.registerItem(copperIngot, "copper_ingot");
        GameRegistry.registerItem(brickMold, "brick_mold");

        // Ore dictionary registration
        OreDictionary.registerOre("ingotCopper", copperIngot);
    }
}