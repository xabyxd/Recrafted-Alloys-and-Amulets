package net.xabyxd.recrafted.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.xabyxd.recrafted.Recrafted;

public class Config {

    public static File configDir = new File("config", Recrafted.MODID);
    public static File configFile = new File(configDir, Recrafted.MODID + ".cfg");

    public static String ConfigTest = "Config test.";

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

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}