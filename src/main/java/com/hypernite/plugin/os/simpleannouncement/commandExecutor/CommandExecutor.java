package com.hypernite.plugin.os.simpleannouncement.commandExecutor;

import com.hypernite.plugin.os.simpleannouncement.commands.BroadcastCommand;
import com.hypernite.plugin.os.simpleannouncement.commands.HelpCommand;
import com.hypernite.plugin.os.simpleannouncement.commands.P2PCommand;
import com.hypernite.plugin.os.simpleannouncement.commands.ReloadCommand;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (args[0]) {
            case "reload":
                new ReloadCommand(sender).execute();
                break;

            case "announce":
                new BroadcastCommand(sender, args[1]).execute();
                break;

            case "send":
                new P2PCommand(sender, args[1], args[2]).execute();
                break;

            default:
                sender.sendMessage(ConfigManger.prefix + ChatColor.RED + " <" + args[0] + "> sub-command not found");
        }

        if(args[0].isEmpty()) {
            new HelpCommand(sender).execute();
        }

        return true;
    }
}
