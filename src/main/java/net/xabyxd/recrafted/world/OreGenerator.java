package net.xabyxd.recrafted.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.config.Config;
import net.xabyxd.recrafted.registers.ModBlocks;

public class OreGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (!Config.EnableOreGen) return;
        if (Compat.IC2Loaded) return;
        // Only Overworld
        if (world.provider.dimensionId != 0) return;
        //          block                veta         tries     Ymin    Ymax
        generateOre(ModBlocks.copperOre, 5, 13, 0, 64, world, random, chunkX, chunkZ);
        generateOre(ModBlocks.tinOre, 4, 12, 0, 64, world, random, chunkX, chunkZ);
    }
    
    private void generateOre(Block block, int veinSize, int tries, int minY, int maxY, World world, Random random, int chunkX, int chunkZ) {
        WorldGenMinable generator = new WorldGenMinable(block, veinSize);
        for (int i = 0; i < tries; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minY + random.nextInt(maxY - minY);
            int z = chunkZ * 16 + random.nextInt(16);
            generator.generate(world, random, x, y, z);
        }
    }
}