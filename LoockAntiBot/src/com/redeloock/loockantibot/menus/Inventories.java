package com.redeloock.loockantibot.menus;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import com.redeloock.loockantibot.skull.Skull;
import com.redeloock.loockantibot.utils.Data;

public class Inventories {
	public static Inventory MenuPrimeiraVez(Player p) {
		Data.manuseando.add(p.getUniqueId());
		Inventory Menu;
		Menu = Bukkit.createInventory(p, 27, "§e§fClique no Correto.");
		// itens
		List<String> stringLore = new ArrayList<>();
		for (int i = 0; i < 27; i++) {
			stringLore = new ArrayList<>();
			stringLore.add("§e" + i);
			stringLore.add("");
			Menu.setItem(i, Item.setLore(Skull.skullNum[i], "§e", stringLore, i));
		}
		return Menu;
	}
}
