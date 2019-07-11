package com.birthdates.hardcoremc.commands;

import com.birthdates.hardcoremc.HardcoreMC;
import com.birthdates.hardcoremc.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class DeathUnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("hardcoremc.admin")) {
            commandSender.sendMessage(Color.Translate("&cYou don't have permission to perform this command!"));
            return false;
        }
        if(strings.length < 1) {
            commandSender.sendMessage(Color.Translate("&cUsage: /" + s + " <uuid>"));
            return false;
        }
        String UUID = strings[0];
        if (!HardcoreMC.getInstance().getBanned().contains(UUID)) {
            commandSender.sendMessage(Color.Translate("&cThat UUID isn't banned."));
            return false;
        }
        HardcoreMC.getInstance().getBanned().remove(UUID);
        commandSender.sendMessage(Color.Translate("&aYou have unbanned the UUID &2" + UUID));
        return false;
    }
}
