package net.xabyxd.recrafted.blocks.machines;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class CokeOvenController extends Block {
    
    public CokeOvenController() {
        super(Material.rock);
        setBlockName("coke_oven_controller");
        setBlockTextureName(Recrafted.MODID + ":coke_oven_controller");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }

    // Use a 3 part texture; 0=sides, 1=front off, 2=front on
    
}