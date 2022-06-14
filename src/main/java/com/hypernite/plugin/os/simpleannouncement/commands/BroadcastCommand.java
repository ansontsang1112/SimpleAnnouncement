package com.hypernite.plugin.os.simpleannouncement.commands;

import com.hypernite.plugin.os.simpleannouncement.interfaces.CommandInterface;
import com.hypernite.plugin.os.simpleannouncement.main.SimpleAnnouncement;
import com.hypernite.plugin.os.simpleannouncement.manager.ConfigManger;
import com.hypernite.plugin.os.simpleannouncement.utils.ColorManager;
import com.hypernite.plugin.os.simpleannouncement.utils.NullChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class BroadcastCommand implements CommandInterface {
    private CommandSender commandSender;
    private String [] args;

    public BroadcastCommand(CommandSender commandSender, String [] args) {
        this.commandSender = commandSender;
        this.args = args;
    }

    @Override
    public void execute() {
        if(NullChecker.isNull(args, 2)) {
            commandSender.sendMessage(ConfigManger.prefix + " &cMessage cannot be null");
            return;
        }

        if(commandSender instanceof Player) {
            if (!commandSender.hasPermission("sap.admin")) {
                commandSender.sendMessage(ConfigManger.prefix + " &c" + ConfigManger.permissionDenied);
                return;
            }
        }

        ArrayList<String> messageArray = new ArrayList();

        Arrays.stream(args).forEach(arg->{
            if(!arg.equalsIgnoreCase(SimpleAnnouncement.COMMAND_PREFIX) && !arg.equalsIgnoreCase("announce")) {
                messageArray.add(arg);
            }
        });

        String formattedMessage = ColorManager.replace(ConfigManger.broadcastMessageFormat
                .replace("%admin%", (commandSender instanceof Player) ? commandSender.getName() : "console")
                .replace("%message%", String.join(" ", messageArray))
                .replace("%b_prefix%", ConfigManger.broadcastPrefix)
        );

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(formattedMessage);
        }

        commandSender.sendMessage(ConfigManger.prefix + ChatColor.GREEN + " " + ConfigManger.delivered);
    }
}
