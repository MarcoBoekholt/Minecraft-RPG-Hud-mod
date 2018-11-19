package me.mabo.mod.handlers;

import me.mabo.mod.Main;
import me.mabo.mod.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static String CATEGORY_MOD = "Experience Bar";

    public static boolean updateCheck = true;
    public static int hudType = 0;

    public static void init(String configDir) {
        if (configuration == null) {
            File path = new File(configDir + "/" + Reference.MODID + ".cfg");

            configuration = new Configuration(path);
            loadConfiguration();
        }
        Main.logger.info("config Initialized");
    }

    private static void loadConfiguration() {
        updateCheck = configuration.getBoolean("Check for Updates", CATEGORY_MOD, true, "Allow this mod to check for updates");
        hudType = configuration.getInt("Hud Type", CATEGORY_MOD, 0, 0, 2, "Which hud type do you want?");

        if(configuration.hasChanged()) {
            configuration.save();
            Main.logger.info("Config saved");
        }

    }

    @SubscribeEvent
    public void onConfigurationChangeEvenet(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Reference.MODID)) {
            loadConfiguration();
        }
    }

    public static Configuration getConfiguration() { return configuration; }

}
