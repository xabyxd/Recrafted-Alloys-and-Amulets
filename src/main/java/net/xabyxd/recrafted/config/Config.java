package net.xabyxd.recrafted.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.xabyxd.recrafted.Recrafted;

public class Config {

    public static File configDir = new File("config", Recrafted.MODID);
    public static File configFile = new File(configDir, Recrafted.MODID + ".cfg");

    public static final String CATEGORY_WORLDGEN = "worldgen";

    public static String ConfigTest = "Config test.";

    // World generation
    public static boolean EnableOreGen = true;
    public static boolean IgnoreIC2Check = false;
    public static boolean GenerateCopperOre = true;
    public static boolean GenerateTinOre = true;
    public static boolean GenerateSilverOre = true;
    public static boolean GenerateLeadOre = true;

    public static void synchronizeConfiguration() {
        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdirs();
        }
        Configuration configuration = new Configuration(configFile);

        ConfigTest = configuration.getString(
            "ConfigTest",
            Configuration.CATEGORY_GENERAL,
            ConfigTest,
            "How shall I greet?"
        );

        EnableOreGen = configuration.getBoolean(
            "EnableOreGen",
            CATEGORY_WORLDGEN,
            EnableOreGen,
            "Master switch. If false, the mod generates no ores at all."
        );

        IgnoreIC2Check = configuration.getBoolean(
            "IgnoreIC2Check",
            CATEGORY_WORLDGEN,
            IgnoreIC2Check,
            "If true, Recrafted generates its ores even when IndustrialCraft 2 is loaded."
        );

        GenerateCopperOre = configuration.getBoolean(
            "GenerateCopperOre",
            CATEGORY_WORLDGEN,
            GenerateCopperOre,
            "Generate copper ore in the overworld."
        );
        GenerateTinOre = configuration.getBoolean(
            "GenerateTinOre",
            CATEGORY_WORLDGEN,
            GenerateTinOre,
            "Generate tin ore in the overworld."
        );
        GenerateSilverOre = configuration.getBoolean(
            "GenerateSilverOre",
            CATEGORY_WORLDGEN,
            GenerateSilverOre,
            "Generate silver ore in the overworld."
        );
        GenerateLeadOre = configuration.getBoolean(
            "GenerateLeadOre",
            CATEGORY_WORLDGEN,
            GenerateLeadOre,
            "Generate lead ore in the overworld."
        );

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}