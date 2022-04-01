package com.hypernite.plugin.os.simpleannouncement.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigManger {
    // Config Vars
    public static List<?> messages;
    public static String prefix;
    public static int refreshPeriod, nextAnnouncementPeriod;
    public static boolean isEnable;

    // Pre-defined Code
    private static ConfigManger configManger;
    private static FileConfiguration fileConfiguration;
    private File config;

    private ConfigManger(Plugin plugin) {
        config = new File(plugin.getDataFolder(), "config.yml");
        if(!config.exists()) {
            plugin.saveResource("config.yml", true);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(config);
    }

    public static ConfigManger getInstance(Plugin plugin) {
        if(configManger == null) {
            configManger =  new ConfigManger(plugin);
        }
        return configManger;
    }

    public void loadConfiguration() {
        isEnable = fileConfiguration.getBoolean("settings.enable");
        refreshPeriod = fileConfiguration.getInt("settings.refresh-period");
        nextAnnouncementPeriod = fileConfiguration.getInt("settings.next-announcement-period");
        prefix = fileConfiguration.getString("settings.announcement-prefix");
        messages = fileConfiguration.getList("announcements");
    }
}
