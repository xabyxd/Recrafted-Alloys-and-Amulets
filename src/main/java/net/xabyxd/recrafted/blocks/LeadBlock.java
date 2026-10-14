package net.xabyxd.recrafted.blocks;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class LeadBlock extends Block {
    
    public LeadBlock() {
        super(Material.iron);
        setBlockName("lead_block");
        setBlockTextureName(Recrafted.MODID + ":lead_block");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 2);
    }
}