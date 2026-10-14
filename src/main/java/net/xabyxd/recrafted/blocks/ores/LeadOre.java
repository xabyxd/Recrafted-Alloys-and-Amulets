package net.xabyxd.recrafted.blocks.ores;

import static net.xabyxd.recrafted.CommonProxy.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.xabyxd.recrafted.Recrafted;

public class LeadOre extends Block {
    
    public LeadOre() {
        super(Material.rock);
        setBlockName("lead_ore");
        setBlockTextureName(Recrafted.MODID + ":lead_ore");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 1);
    }
}