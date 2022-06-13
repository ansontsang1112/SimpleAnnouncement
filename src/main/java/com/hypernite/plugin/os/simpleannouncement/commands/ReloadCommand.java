package com.hypernite.plugin.os.simpleannouncement.commands;

import com.hypernite.plugin.os.simpleannouncement.interfaces.CommandInterface;
import com.hypernite.plugin.os.simpleannouncement.main.SimpleAnnouncement;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandInterface {
    private CommandSender commandSender;

    public ReloadCommand(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        if(commandSender instanceof Player) {
            if(!commandSender.hasPermission("sap.admin")) {
                commandSender.sendMessage(ConfigManger.prefix + "&cPermission Denied.");
                return;
            }
        }

        ConfigManger.getInstance(SimpleAnnouncement.getPlugin(SimpleAnnouncement.class)).reloadConfig();

        commandSender.sendMessage(ConfigManger.prefix + ChatColor.GREEN + " Simple announcement reloaded successfully.");
    }

}
