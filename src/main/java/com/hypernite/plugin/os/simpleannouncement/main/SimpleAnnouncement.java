package com.hypernite.plugin.os.simpleannouncement.main;

import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class SimpleAnnouncement extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Load Configuration
        ConfigManger.getInstance(plugin).loadConfiguration();
        this.getLogger().info(ColorManager.replace("[ Simple Announcement ] : &aPlugin Enabled Successfully."));

        if(ConfigManger.isEnable) {
            // Plugin Enable Logic
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(
                    this, ()->{
                        for (Object message : ConfigManger.messages) {
                            // Message Logic
                        }
                    }, 0, (ConfigManger.refreshPeriod * 20 * 1L)
            );
        } else {
            this.getLogger().info(ColorManager.replace("[ Simple Announcement ] : &ePlease enable the plugin by changing the 'enable' from false to true."));
            onDisable();
        }
    }

    @Override
    public void onDisable() {
        // Plugin Disable Logic
        this.getLogger().info(ColorManager.replace("[ Simple Announcement ] : &cPlugin Disabled Successfully."));
    }
}
