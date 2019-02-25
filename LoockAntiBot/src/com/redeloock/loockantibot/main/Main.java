package com.redeloock.loockantibot.main;

import java.net.InetSocketAddress;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.redeloock.loockantibot.events.OnInventoryClickEvent;
import com.redeloock.loockantibot.events.OnInventoryCloseEvent;
import com.redeloock.loockantibot.events.OnPlayerCommandPreprocessEvent;
import com.redeloock.loockantibot.events.OnPlayerJoinEvent;
import com.redeloock.loockantibot.events.OnPlayerQuitEvent;
import com.redeloock.loockantibot.skull.Skull;
import com.redeloock.loockantibot.utils.Data;
import com.redeloock.loockantibot.utils.GeoLocation;

public class Main extends JavaPlugin implements Listener {

	public static Server server;
	public static GeoLocation geoLocationServer;
	public static boolean iniciando;

	@Override
	public void onEnable() {
		iniciando = true;
		server = Bukkit.getServer();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerJoinEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryCloseEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerQuitEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerCommandPreprocessEvent(), this);

		Data.Importar();
		Skull.SkullCarregar();
		try {
			geoLocationServer = new GeoLocation(new InetSocketAddress(server.getIp(), server.getPort()));
			Bukkit.getConsoleSender().sendMessage(
					"Info: Servidor: " + geoLocationServer.getCountry() + ": " + geoLocationServer.getState());
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cImpossivel acessar dados do Servidor");
		}
		Bukkit.getConsoleSender().sendMessage("§eSistema de AntiBot ativado!");
		Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
			public void run() {
				iniciando = false;
				Bukkit.getConsoleSender().sendMessage("§eEntrada permitida!");
			}
		}, 20 * Data.waitStart);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§cSistema de AntiBot desativado!");
	}
}
