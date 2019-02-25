package com.redeloock.loockantibot.events;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.redeloock.loockantibot.menus.Inventories;
import com.redeloock.loockantibot.utils.Data;

public class OnPlayerCommandPreprocessEvent implements Listener {
	@EventHandler
	public void onComando(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().toUpperCase().contains("/L") || e.getMessage().toUpperCase().contains("/R")) {
			if (p != null) {
				if (Data.playersPrimeiraVez.containsKey(p.getUniqueId())) {
					Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
						public void run() {
							p.openInventory(Inventories.MenuPrimeiraVez(p));
							Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
								public void run() {
									if (Data.playersPrimeiraVez.containsKey(p.getUniqueId())) {
										if (p.isOnline()) {
											Random r = new Random();
											int iRandom = r.nextInt(27);
											Data.playersPrimeiraVez.put(p.getUniqueId(), iRandom);
											p.kickPlayer(
													Data.mensagemKickPrimeiraVez.replace("<numero>", "" + iRandom));
										}
									}
								}
							}, 20 * 15);
						}
					}, 20);
				}
			}
		}
	}
}
