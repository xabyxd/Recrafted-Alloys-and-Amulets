package net.xabyxd.recrafted.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import net.xabyxd.recrafted.blocks.CopperBlock;
import net.xabyxd.recrafted.blocks.ores.CopperOre;
import net.xabyxd.recrafted.blocks.ores.TinOre;

public class ModBlocks {

    public static Block copperOre;
    public static Block tinOre;

    public static Block copperBlock;

    public static void init() {
        // Block builder
        copperOre = new CopperOre();
        tinOre = new TinOre();
        
        copperBlock = new CopperBlock();

        // Block registration
        GameRegistry.registerBlock(copperOre, "copper_ore");
        GameRegistry.registerBlock(tinOre, "tin_ore");
        GameRegistry.registerBlock(copperBlock, "copper_block");

        // Ore dictionary (after registering)
        OreDictionary.registerOre("oreCopper", copperOre);
        OreDictionary.registerOre("oreTin", tinOre);
        OreDictionary.registerOre("blockCopper", copperBlock);
    }
}