package net.xabyxd.recrafted.blocks.ores;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class CopperOre extends Block {
    
    public CopperOre() {
        super(Material.rock);
        setBlockName("copper_ore");
        setBlockTextureName(Recrafted.MODID + ":copper_ore");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 1); // Stone pickaxe or better
    }
}