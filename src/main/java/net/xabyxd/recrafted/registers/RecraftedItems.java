package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.items.bricks.CokeOvenBrick;
import net.xabyxd.recrafted.items.bricks.UnfiredClayBrick;
import net.xabyxd.recrafted.items.bricks.UnfiredCokeOvenBrick;
import net.xabyxd.recrafted.items.fuels.CoalCoke;
import net.xabyxd.recrafted.items.ingots.BronzeIngot;
import net.xabyxd.recrafted.items.ingots.CopperIngot;
import net.xabyxd.recrafted.items.ingots.LeadIngot;
import net.xabyxd.recrafted.items.ingots.SilverIngot;
import net.xabyxd.recrafted.items.ingots.SteelIngot;
import net.xabyxd.recrafted.items.ingots.TinIngot;
import net.xabyxd.recrafted.items.nuggets.BronzeNugget;
import net.xabyxd.recrafted.items.nuggets.CopperNugget;
import net.xabyxd.recrafted.items.nuggets.IronNugget;
import net.xabyxd.recrafted.items.nuggets.LeadNugget;
import net.xabyxd.recrafted.items.nuggets.SilverNugget;
import net.xabyxd.recrafted.items.nuggets.SteelNugget;
import net.xabyxd.recrafted.items.nuggets.TinNugget;
import net.xabyxd.recrafted.items.tools.copper.CopperAxe;
import net.xabyxd.recrafted.items.tools.copper.CopperHoe;
import net.xabyxd.recrafted.items.tools.copper.CopperPickaxe;
import net.xabyxd.recrafted.items.tools.copper.CopperShovel;
import net.xabyxd.recrafted.items.tools.copper.CopperSword;
import net.xabyxd.recrafted.items.tools.wood.BrickMold;

public class RecraftedItems {

    // Tool materials registration
    public static ToolMaterial COPPER;
    public static ToolMaterial TIN;
    public static ToolMaterial BRONZE;
    public static ToolMaterial SILVER;

    // Ingot registration
    public static Item copperIngot;
    public static Item tinIngot;
    public static Item bronzeIngot;
    public static Item silverIngot;
    public static Item leadIngot;
    public static Item steelIngot;

    // Nugget registration
    public static Item ironNugget;
    public static Item copperNugget;
    public static Item tinNugget;
    public static Item bronzeNugget;
    public static Item silverNugget;
    public static Item leadNugget;
    public static Item steelNugget;

    // Tool registration
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperSword;
    public static Item copperShovel;
    public static Item copperHoe;

    // Item registration
    public static Item brickMold;
    public static Item unfiredClayBrick;
    public static Item unfiredCokeOvenBrick;
    public static Item cokeOvenBrick;
    public static Item coalCoke;

    public static void init() {
        // Tool material builder
        COPPER = EnumHelper.addToolMaterial("COPPER", 2, 190, 5.0F, 1.5F, 8);
        TIN = EnumHelper.addToolMaterial("TIN", 2, 132, 3.7F, 1.1F, 8);
        BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 390, 6.0F, 1.9F, 8);
        SILVER = EnumHelper.addToolMaterial("SILVER", 2, 200, 5.3F, 2.7F, 8);

        // Tool builder
        copperPickaxe = new CopperPickaxe(COPPER);
        copperAxe = new CopperAxe(COPPER);
        copperSword = new CopperSword(COPPER);
        copperShovel = new CopperShovel(COPPER);
        copperHoe = new CopperHoe(COPPER);

        // Ingots builder
        copperIngot = new CopperIngot();
        tinIngot = new TinIngot();
        bronzeIngot = new BronzeIngot();
        silverIngot = new SilverIngot();
        leadIngot = new LeadIngot();
        steelIngot = new SteelIngot();

        // Nuggets builder
        ironNugget = new IronNugget();
        copperNugget = new CopperNugget();
        tinNugget = new TinNugget();
        bronzeNugget = new BronzeNugget();
        silverNugget = new SilverNugget();
        leadNugget = new LeadNugget();
        steelNugget = new SteelNugget();

        // Item builder
        brickMold = new BrickMold();
        unfiredClayBrick = new UnfiredClayBrick();
        unfiredCokeOvenBrick = new UnfiredCokeOvenBrick();
        cokeOvenBrick = new CokeOvenBrick();
        coalCoke = new CoalCoke();

        // Tool registration
        GameRegistry.registerItem(copperPickaxe, "copper_pickaxe");
        GameRegistry.registerItem(copperAxe, "copper_axe");
        GameRegistry.registerItem(copperSword, "copper_sword");
        GameRegistry.registerItem(copperShovel, "copper_shovel");
        GameRegistry.registerItem(copperHoe, "copper_hoe");
        
        // Ingot registration
        GameRegistry.registerItem(copperIngot, "copper_ingot");
        GameRegistry.registerItem(tinIngot, "tin_ingot");
        GameRegistry.registerItem(bronzeIngot, "bronze_ingot");
        GameRegistry.registerItem(silverIngot, "silver_ingot");
        GameRegistry.registerItem(leadIngot, "lead_ingot");
        GameRegistry.registerItem(steelIngot, "steel_ingot");

        // Nugget registration
        GameRegistry.registerItem(ironNugget, "iron_nugget");
        GameRegistry.registerItem(copperNugget, "copper_nugget");
        GameRegistry.registerItem(tinNugget, "tin_nugget");
        GameRegistry.registerItem(bronzeNugget, "bronze_nugget");
        GameRegistry.registerItem(silverNugget, "silver_nugget");
        GameRegistry.registerItem(leadNugget, "lead_nugget");
        GameRegistry.registerItem(steelNugget, "steel_nugget");

        // Item registration
        GameRegistry.registerItem(brickMold, "brick_mold");
        GameRegistry.registerItem(unfiredClayBrick, "unfired_clay_brick");
        GameRegistry.registerItem(unfiredCokeOvenBrick, "unfired_coke_oven_brick");
        GameRegistry.registerItem(cokeOvenBrick, "coke_oven_brick");
        GameRegistry.registerItem(coalCoke, "coal_coke");

        // Ore dictionary registration
        OreDictionary.registerOre("ingotCopper", copperIngot);
        OreDictionary.registerOre("ingotTin", tinIngot);
        OreDictionary.registerOre("ingotBronze", bronzeIngot);
        OreDictionary.registerOre("ingotSilver", silverIngot);
        OreDictionary.registerOre("ingotLead", leadIngot);
        OreDictionary.registerOre("ingotSteel", steelIngot);

        OreDictionary.registerOre("nuggetIron", ironNugget);
        OreDictionary.registerOre("nuggetCopper", copperNugget);
        OreDictionary.registerOre("nuggetTin", tinNugget);
        OreDictionary.registerOre("nuggetBronze", bronzeNugget);
        OreDictionary.registerOre("nuggetSilver", silverNugget);
        OreDictionary.registerOre("nuggetLead", leadNugget);
        OreDictionary.registerOre("nuggetSteel", steelNugget);
    }
}