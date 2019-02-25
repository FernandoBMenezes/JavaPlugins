package com.monderdragon.loockeventos.arquivos;

import java.io.File;
import org.bukkit.Bukkit;

public class Arquivo {

	public static File pegarLocal() {
		return Bukkit.getPluginManager().getPlugin("LoockEventos").getDataFolder();
	}

	public static String pegarLoc() {
		String local = Bukkit.getPluginManager().getPlugin("LoockEventos").getDataFolder().getAbsolutePath()
				.replace("/LoockEventos", "");
		Leitura.Linher(new File(local));
		return local;
	}

	public static boolean Existe(File f) {
		return f.exists();
	}

	public static void criarBase() {
		File localBase = new File(pegarLocal().getAbsolutePath());
		File localConfigurar = new File(pegarLocal().getAbsolutePath() + "/Eventos.yml");
		File localTagGuerra = new File(pegarLocal().getAbsolutePath() + "/TagGuerra.yml");
		if (!Existe(localBase))
			Criar.Pasta(localBase);
		if (!Existe(localConfigurar))
			Criar.Bloco(localConfigurar);
		if (!Existe(localTagGuerra))
			Criar.Pasta(localTagGuerra);
	}
}
