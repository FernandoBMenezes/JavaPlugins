package com.redeloock.loockantibot.menus;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	public static ItemStack setLore(Material material, String nome, List<String> loreString, int quantidade) {
		ItemStack itemLore = new ItemStack(material, quantidade);
		ItemMeta itemMeta = itemLore.getItemMeta();
		itemMeta.setDisplayName(nome);
		itemMeta.setLore(loreString);
		itemLore.setItemMeta(itemMeta);
		return itemLore;
	}

	public static ItemStack setLore(ItemStack item, String nome, List<String> loreString, int quantidade) {
		item.setAmount(quantidade);
		ItemStack itemLore = item;
		ItemMeta itemMeta = itemLore.getItemMeta();
		itemMeta.setDisplayName(nome);
		itemMeta.setLore(loreString);
		itemLore.setItemMeta(itemMeta);
		return itemLore;
	}

	public static ItemStack setLore(Material material, Short color, String nome, List<String> loreString,
			int quantidade) {
		ItemStack itemLore = new ItemStack(material, quantidade, color);
		ItemMeta itemMeta = itemLore.getItemMeta();
		itemMeta.setDisplayName(nome);
		itemMeta.setLore(loreString);
		itemLore.setItemMeta(itemMeta);
		return itemLore;
	}
}