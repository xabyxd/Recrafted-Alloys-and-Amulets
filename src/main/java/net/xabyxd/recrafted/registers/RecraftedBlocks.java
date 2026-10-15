package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.blocks.BronzeBlock;
import net.xabyxd.recrafted.blocks.CokeOvenBricks;
import net.xabyxd.recrafted.blocks.CopperBlock;
import net.xabyxd.recrafted.blocks.LeadBlock;
import net.xabyxd.recrafted.blocks.SilverBlock;
import net.xabyxd.recrafted.blocks.SteelBlock;
import net.xabyxd.recrafted.blocks.TinBlock;
import net.xabyxd.recrafted.blocks.machines.CokeOvenController;
import net.xabyxd.recrafted.blocks.ores.CopperOre;
import net.xabyxd.recrafted.blocks.ores.LeadOre;
import net.xabyxd.recrafted.blocks.ores.SilverOre;
import net.xabyxd.recrafted.blocks.ores.TinOre;

public class RecraftedBlocks {

    public static Block copperOre;
    public static Block tinOre;
    public static Block silverOre;
    public static Block leadOre;

    public static Block copperBlock;
    public static Block tinBlock;
    public static Block silverBlock;
    public static Block leadBlock;
    public static Block steelBlock;
    public static Block bronzeBlock;

    public static Block cokeOvenBricks;
    public static Block cokeOvenController;

    public static void init() {
        // Block builder
        copperOre = new CopperOre();
        tinOre = new TinOre();
        silverOre = new SilverOre();
        leadOre = new LeadOre();
        
        copperBlock = new CopperBlock();
        tinBlock = new TinBlock();
        silverBlock = new SilverBlock();
        leadBlock = new LeadBlock();
        steelBlock = new SteelBlock();
        bronzeBlock = new BronzeBlock();

        cokeOvenBricks = new CokeOvenBricks();
        cokeOvenController = new CokeOvenController();

        // Block registration
        GameRegistry.registerBlock(copperOre, "copper_ore");
        GameRegistry.registerBlock(tinOre, "tin_ore");
        GameRegistry.registerBlock(silverOre, "silver_ore");
        GameRegistry.registerBlock(leadOre, "lead_ore");
        GameRegistry.registerBlock(copperBlock, "copper_block");
        GameRegistry.registerBlock(tinBlock, "tin_block");
        GameRegistry.registerBlock(silverBlock, "silver_block");
        GameRegistry.registerBlock(leadBlock, "lead_block");
        GameRegistry.registerBlock(steelBlock, "steel_block");
        GameRegistry.registerBlock(bronzeBlock, "bronze_block");

        GameRegistry.registerBlock(cokeOvenBricks, "coke_oven_bricks");
        GameRegistry.registerBlock(cokeOvenController, "coke_oven_controller");

        // Ore dictionary (after registering)
        // Ores:
        OreDictionary.registerOre("oreCopper", copperOre);
        OreDictionary.registerOre("oreTin", tinOre);
        OreDictionary.registerOre("oreSilver", silverOre);
        OreDictionary.registerOre("oreLead", leadOre);

        // Blocks:
        OreDictionary.registerOre("blockCopper", copperBlock);
        OreDictionary.registerOre("blockTin", tinBlock);
        OreDictionary.registerOre("blockSilver", silverBlock);
        OreDictionary.registerOre("blockLead", leadBlock);
        OreDictionary.registerOre("blockSteel", steelBlock);
        OreDictionary.registerOre("blockBronze", bronzeBlock);
    }
}