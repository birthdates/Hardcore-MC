package com.birthdates.hardcoremc.listeners;

import com.birthdates.hardcoremc.HardcoreMC;
import com.birthdates.hardcoremc.utils.Color;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDie(PlayerDeathEvent e) {
        if(!HardcoreMC.getInstance().getConfigurationManager().getDifficulty().equals(Difficulty.HARD)) return;
        Player player = e.getEntity();
        if(player.hasPermission("hardcoremc.bypass")) {
            player.sendMessage(Color.Translate("&aYou have bypassed your hardcore death ban."));
            return;
        }
        HardcoreMC.getInstance().getBanned().add(player.getUniqueId().toString());
        player.kickPlayer(HardcoreMC.getInstance().getConfigurationManager().getKickMessage());
    }

}
