package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.blocks.BronzeBlock;
import net.xabyxd.recrafted.blocks.CoalCokeBlock;
import net.xabyxd.recrafted.blocks.CokeOvenBricks;
import net.xabyxd.recrafted.blocks.CopperBlock;
import net.xabyxd.recrafted.blocks.LeadBlock;
import net.xabyxd.recrafted.blocks.SilverBlock;
import net.xabyxd.recrafted.blocks.SteelBlock;
import net.xabyxd.recrafted.blocks.TinBlock;
import net.xabyxd.recrafted.blocks.machines.CokeOvenController;
import net.xabyxd.recrafted.blocks.ores.CopperOre;
import net.xabyxd.recrafted.blocks.ores.LeadOre;
import net.xabyxd.recrafted.blocks.ores.PeridotOre;
import net.xabyxd.recrafted.blocks.ores.RubyOre;
import net.xabyxd.recrafted.blocks.ores.SapphireOre;
import net.xabyxd.recrafted.blocks.ores.SilverOre;
import net.xabyxd.recrafted.blocks.ores.TinOre;

public class RecraftedBlocks {

    public static Block copperOre;
    public static Block tinOre;
    public static Block silverOre;
    public static Block leadOre;
    public static Block rubyOre;
    public static Block sapphireOre;
    public static Block peridotOre;

    public static Block copperBlock;
    public static Block tinBlock;
    public static Block silverBlock;
    public static Block leadBlock;
    public static Block steelBlock;
    public static Block bronzeBlock;

    public static Block cokeOvenBricks;
    public static Block cokeOvenController;
    public static Block coalCokeBlock;

    public static void init() {
        // Block builder
        copperOre = new CopperOre();
        tinOre = new TinOre();
        silverOre = new SilverOre();
        leadOre = new LeadOre();
        rubyOre = new RubyOre();
        sapphireOre = new SapphireOre();
        peridotOre = new PeridotOre();
        
        copperBlock = new CopperBlock();
        tinBlock = new TinBlock();
        silverBlock = new SilverBlock();
        leadBlock = new LeadBlock();
        steelBlock = new SteelBlock();
        bronzeBlock = new BronzeBlock();

        cokeOvenBricks = new CokeOvenBricks();
        cokeOvenController = new CokeOvenController();
        coalCokeBlock = new CoalCokeBlock();

        // Block registration
        GameRegistry.registerBlock(copperOre, "copper_ore");
        GameRegistry.registerBlock(tinOre, "tin_ore");
        GameRegistry.registerBlock(silverOre, "silver_ore");
        GameRegistry.registerBlock(leadOre, "lead_ore");
        GameRegistry.registerBlock(rubyOre, "ruby_ore");
        GameRegistry.registerBlock(sapphireOre, "sapphire_ore");
        GameRegistry.registerBlock(peridotOre, "peridot_ore");
        GameRegistry.registerBlock(copperBlock, "copper_block");
        GameRegistry.registerBlock(tinBlock, "tin_block");
        GameRegistry.registerBlock(silverBlock, "silver_block");
        GameRegistry.registerBlock(leadBlock, "lead_block");
        GameRegistry.registerBlock(steelBlock, "steel_block");
        GameRegistry.registerBlock(bronzeBlock, "bronze_block");

        GameRegistry.registerBlock(cokeOvenBricks, "coke_oven_bricks");
        GameRegistry.registerBlock(cokeOvenController, "coke_oven_controller");
        GameRegistry.registerBlock(coalCokeBlock, "coal_coke_block");

        // Ore dictionary (after registering)
        // Ores:
        OreDictionary.registerOre("oreCopper", copperOre);
        OreDictionary.registerOre("oreTin", tinOre);
        OreDictionary.registerOre("oreSilver", silverOre);
        OreDictionary.registerOre("oreLead", leadOre);
        OreDictionary.registerOre("oreRuby", rubyOre);
        OreDictionary.registerOre("oreSapphire", sapphireOre);
        OreDictionary.registerOre("orePeridot", peridotOre);

        // Blocks:
        OreDictionary.registerOre("blockCopper", copperBlock);
        OreDictionary.registerOre("blockTin", tinBlock);
        OreDictionary.registerOre("blockSilver", silverBlock);
        OreDictionary.registerOre("blockLead", leadBlock);
        OreDictionary.registerOre("blockSteel", steelBlock);
        OreDictionary.registerOre("blockBronze", bronzeBlock);
    }
}