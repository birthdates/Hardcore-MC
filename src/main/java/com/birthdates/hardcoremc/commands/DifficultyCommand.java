package com.birthdates.hardcoremc.commands;

import com.birthdates.hardcoremc.HardcoreMC;
import com.birthdates.hardcoremc.utils.Color;
import org.bukkit.Difficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DifficultyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("hardcoremc.admin")) {
            commandSender.sendMessage(Color.Translate("&cYou don't have permission to perform this command!"));
            return false;
        }
        if(strings.length < 1) {
            commandSender.sendMessage(Color.Translate("&cUsage: /" + s + " <peaceful|normal|hard>"));
            return false;
        }
        try {
            String sDif = strings[0].toUpperCase();
            Difficulty difficulty = Difficulty.valueOf(sDif);
            HardcoreMC.getInstance().getConfigurationManager().setDifficulty(difficulty);
            HardcoreMC.getInstance().getConfigurationManager().saveConfig();
            commandSender.sendMessage(Color.Translate("&aDifficulty updated to &2" + sDif));
        } catch(Exception e) {
            commandSender.sendMessage(Color.Translate("&cUsage: /" + s + " <peaceful|normal|hard>"));
        }
        return false;
    }
}
