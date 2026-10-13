package net.xabyxd.recrafted.blocks;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class TinBlock extends Block {
    
    public TinBlock() {
        super(Material.iron);
        setBlockName("tin_block");
        setBlockTextureName(Recrafted.MODID + ":tin_block");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }
}