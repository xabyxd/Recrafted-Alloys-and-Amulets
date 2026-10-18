package net.xabyxd.recrafted.blocks;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class CoalCokeBlock extends Block {
    
    public CoalCokeBlock() {
        super(Material.rock);
        setBlockName("coal_coke_block");
        setBlockTextureName(Recrafted.MODID + ":coal_coke_block");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }
}