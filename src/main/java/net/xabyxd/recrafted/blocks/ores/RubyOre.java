package net.xabyxd.recrafted.blocks.ores;

import static net.xabyxd.recrafted.CommonProxy.*;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.xabyxd.recrafted.Recrafted;
import net.xabyxd.recrafted.registers.RecraftedItems;

/**
 * Ruby ore. Drops ruby gems (affected by Fortune) and experience.
 * With Silk Touch it drops the ore block itself (handled automatically by vanilla).
 */
public class RubyOre extends Block {

    private final Random xpRandom = new Random();
    
    public RubyOre() {
        super(Material.rock);
        setBlockName("ruby_ore");
        setBlockTextureName(Recrafted.MODID + ":ruby_ore");
        setCreativeTab(RecraftedTAB);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 2);
    }

    /** Gems are dropped when the ore is mined. */
    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return RecraftedItems.rubyGem;
    }

    /** Base amount of gems per ore (1 by default). */
    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    /** Fortune: same formula as vanilla diamond/emerald ore. */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            int bonus = random.nextInt(fortune + 2) - 1;
            if (bonus < 0) bonus = 0;
            return quantityDropped(random) * (bonus + 1);
        }
        return quantityDropped(random);
    }

    /** Experience dropped when mined (not dropped with Silk Touch). */
    @Override
    public int getExpDrop(IBlockAccess world, int meta, int fortune) {
        return MathHelper.getRandomIntegerInRange(xpRandom, 3, 7);
    }
}