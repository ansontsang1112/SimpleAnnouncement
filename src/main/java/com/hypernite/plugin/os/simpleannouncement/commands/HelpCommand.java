package com.hypernite.plugin.os.simpleannouncement.commands;

import com.hypernite.plugin.os.simpleannouncement.interfaces.CommandInterface;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandInterface {
    private CommandSender commandSender;

    public HelpCommand(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        commandSender.sendMessage("Simple Announce Help Menu: \n" +
                "/sap reload: reload plugin \n" +
                "/sap announce <message>: Send announcement to all players\n" +
                "/sap send <player> <message>: Send admin message to a player");
    }
}
