package imbacrian.bapilib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imbacrian.bapilib.BAPILib;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHelper {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /*
     * Loads a JSON config file. If it doesn't exist, it creates a new one by default.
     *
     * file                .json file in disk.
     * defaultClass        The configuration object class to instantiate if it does not exist.
     * T                   The type of the configuration class.
     * return              The instance of the loaded or default configuration.
     */
    public static <T> T loadConfig(File file, Class<T> defaultClass) {
        if (!file.exists()) {
            try {
                // Ensuring parent directory exists to avoid this thing from crashing
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }

                // Creating a new default instance and saving it somewhere.
                T defaultConfig = defaultClass.getDeclaredConstructor().newInstance();
                saveConfig(file, defaultConfig);
                return defaultConfig;
            } catch (Exception e) {
                BAPILib.LOGGER.error("Failed to create default config for: {}", file.getName(), e);
                return null;
            }
        }

        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, defaultClass);
        } catch (IOException e) {
            BAPILib.LOGGER.error("Failed to load config from: {}", file.getName(), e);
            return null;
        }
    }

    /*
     * Saves a configuration object in JSON format to disk.
     *
     * file     The .json file where it will be saved.
     * config   The configuration object to be saved.
     */
    public static void saveConfig(File file, Object config) {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(config, writer);
            BAPILib.LOGGER.info("Config saved successfully: {}", file.getName());
        } catch (IOException e) {
            BAPILib.LOGGER.error("Failed to save config to: {}", file.getName(), e);
        }
    }
}
