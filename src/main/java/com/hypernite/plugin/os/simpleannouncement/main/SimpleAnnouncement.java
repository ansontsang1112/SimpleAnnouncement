package com.hypernite.plugin.os.simpleannouncement.main;

import com.hypernite.plugin.os.simpleannouncement.commandExecutor.CommandExecutor;
import com.hypernite.plugin.os.simpleannouncement.services.LoopableAnnounceServices;
import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleAnnouncement extends JavaPlugin {
    public static Plugin plugin;

    public static final String COMMAND_PREFIX = "sap";

    @Override
    public void onEnable() {
        plugin = this;

        // Load Configuration
        ConfigManger.getInstance(plugin).loadConfiguration();
        this.getLogger().info("[ Simple Announcement ] : Plugin Enabled Successfully.");
        this.getCommand("sap").setExecutor(new CommandExecutor());

        if(ConfigManger.isEnable) {
            // Plugin Enable Logic
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new LoopableAnnounceServices(), 0, ((long) ConfigManger.nextAnnouncementPeriod * 20));
        } else {
            this.getLogger().info(ColorManager.replace("[ Simple Announcement ] : Please enable the plugin by changing the 'enable' from false to true."));
            onDisable();
        }
    }

    @Override
    public void onDisable() {
        // Plugin Disable Logic
        this.getLogger().info(ColorManager.replace("[ Simple Announcement ] : Plugin Disabled Successfully."));
    }
}
