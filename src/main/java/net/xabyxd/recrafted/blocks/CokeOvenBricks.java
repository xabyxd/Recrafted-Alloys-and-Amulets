package net.xabyxd.recrafted.blocks;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class CokeOvenBricks extends Block {
    
    public CokeOvenBricks() {
        super(Material.rock);
        setBlockName("coke_oven_bricks");
        setBlockTextureName(Recrafted.MODID + ":coke_oven_bricks");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }
}