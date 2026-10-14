package net.xabyxd.recrafted.blocks;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class SilverBlock extends Block {
    
    public SilverBlock() {
        super(Material.iron);
        setBlockName("silver_block");
        setBlockTextureName(Recrafted.MODID + ":silver_block");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 2);
    }
}