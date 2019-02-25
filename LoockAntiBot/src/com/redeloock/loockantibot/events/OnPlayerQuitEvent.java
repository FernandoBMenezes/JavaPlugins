package com.redeloock.loockantibot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.redeloock.loockantibot.main.Main;
import com.redeloock.loockantibot.utils.Data;

public class OnPlayerQuitEvent implements Listener {
	@EventHandler
	public void saindoVerificar(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p != null) {
			if (!Main.iniciando) {
				Data.esperarReconectar.add(p.getUniqueId());
				OnPlayerJoinEvent.retirarEsperarReconectar(p.getUniqueId());
			}
		}
	}
}