package com.birthdates.hardcoremc.commands;

import com.birthdates.hardcoremc.HardcoreMC;
import com.birthdates.hardcoremc.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HardcoreMCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(Color.Translate("&7&m-------------------------------------------"));
        commandSender.sendMessage(Color.Translate("&7This server is using &6HardcoreMC &7version &6" + HardcoreMC.getInstance().getDescription().getVersion() + " &7by &6birthdates&7."));
        commandSender.sendMessage(Color.Translate("&7&m-------------------------------------------"));
        return false;
    }
}
