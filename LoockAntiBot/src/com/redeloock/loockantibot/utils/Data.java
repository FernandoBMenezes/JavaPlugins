package com.redeloock.loockantibot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import com.redeloock.loockantibot.workers.State;

public class Data {
	public static int tempoEntrarProximo;
	public static ArrayList<String> countryList = new ArrayList<String>();
	public static int podeEntrar = 0;
	public static int podeEntrarMaximo;
	public static boolean outrosPermitidos;
	public static Plugin plugin = Bukkit.getPluginManager().getPlugin("LoockAntiBot");
	public static HashMap<String, UUID> ipRecente = new HashMap<String, UUID>();
	public static ArrayList<UUID> esperarReconectar = new ArrayList<UUID>();
	public static int esperaReconectarTempo;
	public static int ipRecenteReconectar;
	public static String mensagemKickPrimeiraVez;
	public static ArrayList<UUID> manuseando = new ArrayList<UUID>();
	public static HashMap<UUID, Integer> playersPrimeiraVez = new HashMap<>();
	public static int waitStart;

	public static void Importar() {
		Data.countryList.add("BR");
		outrosPermitidos = true;
		podeEntrarMaximo = 1;
		tempoEntrarProximo = 60;
		ipRecenteReconectar = 120;
		esperaReconectarTempo = 5;
		waitStart = 10;
		State.maxAmountJoin = 15;

		mensagemKickPrimeiraVez = "§cSua conexão está sendo verificada! §lReconecte ao servidor!\n"
				+ "§cApós se §llogar§c clique no numero §e§l<numero>§c no menu.\n" + "§f\n"
				+ "§cEsse processo será realizada apenas uma vez, para proteção do servidor.\n"
				+ "§eSeja bem-vindo a §6§lRede Loock.\n";
	}
}
