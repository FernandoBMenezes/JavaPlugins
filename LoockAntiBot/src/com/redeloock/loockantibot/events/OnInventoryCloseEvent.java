package com.redeloock.loockantibot.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.redeloock.loockantibot.menus.Inventories;
import com.redeloock.loockantibot.utils.Data;

public class OnInventoryCloseEvent implements Listener {
	@EventHandler
	public void fechou(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (p != null) {
			if (Data.playersPrimeiraVez.containsKey(p.getPlayer().getUniqueId())) {
				Location l = p.getLocation();
				Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
					public void run() {
						if (p.isOnline()) {
							p.teleport(l);
							p.openInventory(Inventories.MenuPrimeiraVez(p));
						}
					}
				}, 10);
			}
		}
	}
}
