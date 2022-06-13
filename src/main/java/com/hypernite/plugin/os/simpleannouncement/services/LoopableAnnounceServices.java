package com.hypernite.plugin.os.simpleannouncement.services;

import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoopableAnnounceServices implements Runnable {
    private int messageCount = 0;

    @Override
    public void run() {
        if(messageCount <= ConfigManger.messages.size()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ColorManager.replace(ConfigManger.messages.get(messageCount).toString()));
            }

            messageCount++;
        } else {
            messageCount = 0;
        }

        if (messageCount == ConfigManger.messages.size()) {
            messageCount = 0;
        }
    }
}
