package com.hypernite.plugin.os.simpleannouncement.commands;

import com.hypernite.plugin.os.simpleannouncement.interfaces.CommandInterface;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class P2PCommand implements CommandInterface {
    private CommandSender commandSender;
    private String nameOfPlayer;
    private String message;

    public P2PCommand(CommandSender commandSender, String nameOfPlayer, String message) {
        this.commandSender = commandSender;
        this.nameOfPlayer = nameOfPlayer;
        this.message = message;
    }

    @Override
    public void execute() {
        if(commandSender instanceof Player) {
            if(!commandSender.hasPermission("sap.admin")) {
                commandSender.sendMessage(ConfigManger.prefix + ChatColor.RED + " Permission Denied!");
                return;
            }
        }

        if(getPlayer() == null) {
            commandSender.sendMessage(ConfigManger.prefix + ChatColor.RED + " Player Not Found!");
            return;
        }

        Player player = getPlayer();

        player.sendMessage(ColorManager.replace(ConfigManger.prefix + " " + ConfigManger.adminMessageFormat
                .replace("%admin%", (commandSender instanceof Player) ? commandSender.getName() : "console")
                .replace("%message", message))
        );

        commandSender.sendMessage(ConfigManger.prefix + ChatColor.GREEN + " Message sent successfully.");
        return;
    }

    private Player getPlayer() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getName() == nameOfPlayer) return p;
        }

        return null;
    }
}
