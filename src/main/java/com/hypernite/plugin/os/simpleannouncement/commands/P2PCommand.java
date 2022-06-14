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

public class P2PCommand implements CommandInterface {
    private CommandSender commandSender;
    private String [] args;

    public P2PCommand(CommandSender commandSender, String [] args) {
        this.commandSender = commandSender;
        this.args = args;
    }

    @Override
    public void execute() {
        if(NullChecker.isNull(args, 3)) {
            commandSender.sendMessage(ConfigManger.prefix + ChatColor.RED + " /sap send <player> <message>");
            return;
        }

        if(commandSender instanceof Player) {
            if(!commandSender.hasPermission("sap.admin")) {
                commandSender.sendMessage(ConfigManger.prefix + ChatColor.RED + " " + ConfigManger.permissionDenied);
                return;
            }
        }

        if(getPlayer() == null) {
            commandSender.sendMessage(ConfigManger.prefix + ChatColor.RED + " " + ConfigManger.playerNotFound);
            return;
        }

        Player player = getPlayer();

        ArrayList<String> messageArray = new ArrayList();

        Arrays.stream(args).forEach(arg->{
            if(!arg.equalsIgnoreCase(SimpleAnnouncement.COMMAND_PREFIX) && !arg.equalsIgnoreCase("send") && !arg.equalsIgnoreCase(args[1])) {
                messageArray.add(arg);
            }
        });

        player.sendMessage(ColorManager.replace(ConfigManger.prefix + " " + ConfigManger.adminMessageFormat
                .replace("%admin%", (commandSender instanceof Player) ? commandSender.getName() : "console")
                .replace("%message%", String.join(" ", messageArray))
        ));

        commandSender.sendMessage(ConfigManger.prefix + ChatColor.GREEN + " " + ConfigManger.delivered);
        return;
    }

    private Player getPlayer() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getName().equals(args[1])) return p;
        }
        return null;
    }
}
