package com.hypernite.plugin.os.simpleannouncement.commands;

import com.hypernite.plugin.os.simpleannouncement.interfaces.CommandInterface;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandInterface {
    private CommandSender commandSender;
    private String message;

    public BroadcastCommand(CommandSender commandSender, String message) {
        this.commandSender = commandSender;
        this.message = message;
    }

    @Override
    public void execute() {
       if(message == null || message == "") {
           commandSender.sendMessage(ConfigManger.prefix + " &cMessage cannot be null");
           return;
       }


        if(commandSender instanceof Player) {
            if (!commandSender.hasPermission("sap.admin")) {
                commandSender.sendMessage(ConfigManger.prefix + " &cPermission Denied.");
                return;
            }
        }

        String formattedMessage = ColorManager.replace(ConfigManger.broadcastMessageFormat
                .replace("%admin%", (commandSender instanceof Player) ? commandSender.getName() : "console")
                .replace("%message%", message)
                .replace("%b_prefix%", ConfigManger.broadcastPrefix)
        );

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(formattedMessage);
        }

        commandSender.sendMessage(ConfigManger.prefix + ChatColor.GREEN + " Message sent successfully.");
    }
}
