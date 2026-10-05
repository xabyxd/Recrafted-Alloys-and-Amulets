package net.xabyxd.recrafted;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.xabyxd.recrafted.tools.CopperPickaxe;

public class ModItems {

    public static ToolMaterial COPPER;
    public static Item copperPickaxe;

    public static void init() {
        COPPER = EnumHelper.addToolMaterial("COPPER", 2, 190, 5.0F, 1.5F, 8);

        copperPickaxe = new CopperPickaxe(COPPER);
        GameRegistry.registerItem(copperPickaxe, "copper_pickaxe");
    }
}