package net.xabyxd.recrafted.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.xabyxd.recrafted.compat.Compat;
import net.xabyxd.recrafted.config.Config;
import net.xabyxd.recrafted.registers.RecraftedBlocks;

/**
 * Generates custom ores in the Overworld using the {@link IWorldGenerator} interface.
 *
 * <p>Ore generation can be disabled through the mod configuration or when
 * IndustrialCraft 2 is loaded, to avoid compatibility issues.</p>
 *
 * <p>Currently generates copper, tin, silver and lead ores at configurable heights
 * and frequencies defined by the generation parameters.</p>
 *
 * <p>Each ore can either be affected by its own config boolean (use the overload
 * that takes the boolean as first argument) or ignore any boolean and always
 * generate (use the overload without it).</p>
 *
 * @author xabyxd
 */
public class OreGenerator implements IWorldGenerator {

    /**
     * Generates custom ores in a specific world chunk.
     *
     * @param random the random number generator used for ore placement
     * @param chunkX the X coordinate of the chunk
     * @param chunkZ the Z coordinate of the chunk
     * @param world the world in which ores are generated
     * @param chunkGenerator the world chunk generator
     * @param chunkProvider the chunk provider
     */
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (!Config.EnableOreGen) return;
        if (Compat.IC2Loaded && !Config.IgnoreIC2Check) return;

        // Only generate ores in the Overworld.
        if (world.provider.dimensionId != 0) return;

        // Affected by the config booleans:
        //          Toggle                      RecraftedBlocks.Block       Vein size  Tries  Min Y  Max Y
        generateOre(Config.GenerateCopperOre,   RecraftedBlocks.copperOre,  5,         13,    32,    64,   world, random, chunkX, chunkZ);
        generateOre(Config.GenerateTinOre,      RecraftedBlocks.tinOre,     4,         12,    32,    64,   world, random, chunkX, chunkZ);
        generateOre(Config.GenerateSilverOre,   RecraftedBlocks.silverOre,  4,         11,    40,    45,   world, random, chunkX, chunkZ);
        generateOre(Config.GenerateLeadOre,     RecraftedBlocks.leadOre,    3,         10,    0,     32,   world, random, chunkX, chunkZ);

        // Ignores the config booleans (always generates), no toggle argument:
        generateOre(RecraftedBlocks.rubyOre, 3, 8, 0, 20, world, random, chunkX, chunkZ);
        generateOre(RecraftedBlocks.sapphireOre, 3, 8, 0, 20, world, random, chunkX, chunkZ);
        generateOre(RecraftedBlocks.peridotOre, 3, 8, 0, 20, world, random, chunkX, chunkZ);
    }

    /**
     * Generates a specific ore within a chunk, only if its config toggle is enabled.
     * Use this overload for ores that must be affected by the config booleans.
     *
     * @param enabled the config boolean that controls this ore
     * @param block the ore block to generate
     * @param veinSize the maximum number of blocks in an ore vein
     * @param tries the number of generation attempts per chunk
     * @param minY the minimum generation height (inclusive)
     * @param maxY the maximum generation height (exclusive)
     * @param world the world in which the ore is generated
     * @param random the random number generator used for placement
     * @param chunkX the X coordinate of the chunk
     * @param chunkZ the Z coordinate of the chunk
     */
    private void generateOre(boolean enabled, Block block, int veinSize, int tries, int minY, int maxY, World world, Random random, int chunkX, int chunkZ) {
        if (!enabled) return;
        generateOre(block, veinSize, tries, minY, maxY, world, random, chunkX, chunkZ);
    }

    /**
     * Generates a specific ore within a chunk, ignoring any config boolean.
     * Use this overload for ores that must always generate.
     *
     * @param block the ore block to generate
     * @param veinSize the maximum number of blocks in an ore vein
     * @param tries the number of generation attempts per chunk
     * @param minY the minimum generation height (inclusive)
     * @param maxY the maximum generation height (exclusive)
     * @param world the world in which the ore is generated
     * @param random the random number generator used for placement
     * @param chunkX the X coordinate of the chunk
     * @param chunkZ the Z coordinate of the chunk
     */
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