package com.redeloock.loockantibot.events;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.redeloock.loockantibot.utils.Data;

public class OnInventoryClickEvent implements Listener {

	@EventHandler
	public void clicou(InventoryClickEvent e) {
		if (e.getWhoClicked() != null && e.getWhoClicked() instanceof Player
				&& Data.manuseando.contains(((Player) e.getWhoClicked()).getUniqueId())) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
					&& e.getInventory().getName() != "container.inventory") {
				if (e.getInventory().getName().contains("§e§fClique no Correto.")) {
					e.setCancelled(true);
					int i = Data.playersPrimeiraVez.get(p.getUniqueId());
					if (e.getCurrentItem().getAmount() == i) {
						Data.playersPrimeiraVez.remove(p.getUniqueId());
						p.closeInventory();
					} else {
						Random r = new Random();
						int iRandom = r.nextInt(27);
						Data.playersPrimeiraVez.put(p.getUniqueId(), iRandom);
						p.kickPlayer(Data.mensagemKickPrimeiraVez.replace("<numero>", "" + iRandom));
					}
				}
			}
		}

	}
}
