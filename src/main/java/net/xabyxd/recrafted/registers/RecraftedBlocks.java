package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.blocks.CopperBlock;
import net.xabyxd.recrafted.blocks.TinBlock;
import net.xabyxd.recrafted.blocks.ores.CopperOre;
import net.xabyxd.recrafted.blocks.ores.TinOre;

public class RecraftedBlocks {

    public static Block copperOre;
    public static Block tinOre;

    public static Block copperBlock;
    public static Block tinBlock;

    public static void init() {
        // Block builder
        copperOre = new CopperOre();
        tinOre = new TinOre();
        
        copperBlock = new CopperBlock();
        tinBlock = new TinBlock();

        // Block registration
        GameRegistry.registerBlock(copperOre, "copper_ore");
        GameRegistry.registerBlock(tinOre, "tin_ore");
        GameRegistry.registerBlock(copperBlock, "copper_block");
        GameRegistry.registerBlock(tinBlock, "tin_block");

        // Ore dictionary (after registering)
        OreDictionary.registerOre("oreCopper", copperOre);
        OreDictionary.registerOre("oreTin", tinOre);
        OreDictionary.registerOre("blockCopper", copperBlock);
        OreDictionary.registerOre("blockTin", tinBlock);
    }
}