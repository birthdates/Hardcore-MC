package com.birthdates.hardcoremc.listeners;

import com.birthdates.hardcoremc.HardcoreMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ConnectionListener implements Listener {


    @EventHandler
    public void onConnect(PlayerLoginEvent e) {
        if(HardcoreMC.getInstance().getBanned().contains(e.getPlayer().getUniqueId().toString())) {
            if(e.getPlayer().hasPermission("hardcoremc.bypass")) {
                HardcoreMC.getInstance().getBanned().remove(e.getPlayer().getUniqueId().toString());
                return;
            }
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, HardcoreMC.getInstance().getConfigurationManager().getKickMessage());
        }
    }


}
