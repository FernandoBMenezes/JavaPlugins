package com.redeloock.loockantibot.events;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.redeloock.loockantibot.main.Main;
import com.redeloock.loockantibot.utils.Data;
import com.redeloock.loockantibot.utils.GeoLocation;
import com.redeloock.loockantibot.workers.State;

public class OnPlayerJoinEvent implements Listener {
	public static int tempoParaEntrar = Data.tempoEntrarProximo;
	public static boolean reduzindoTempo = false;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void entrouVerificar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p != null) {
			if (p.hasPlayedBefore() == false) {
				Random r = new Random();
				int iRandom = r.nextInt(27);
				Data.playersPrimeiraVez.put(p.getUniqueId(), iRandom);
				p.kickPlayer(Data.mensagemKickPrimeiraVez.replace("<numero>", "" + iRandom));
				return;
			}
			if (Main.iniciando) {
				p.kickPlayer("§cImpossivel entrar aguarde um pouco!\nServidor esta §eIniciando");
				return;
			}
			InetSocketAddress ip = e.getPlayer().getAddress();
			if (Data.ipRecente.containsKey(ip.getHostName())) {
				if (!Data.ipRecente.get(ip.getHostName()).equals(p.getUniqueId())) {
					p.kickPlayer(
							"§cImpossivel entrar aguarde um pouco!\nEste ip ja se conectou recentemente em outra conta!");
					return;
				} else {
					Data.ipRecente.put(ip.getHostName(), p.getUniqueId());
					retirarIpRecente(ip.getHostName());
				}
			}
			if (Data.esperarReconectar.contains(p.getUniqueId())) {
				p.kickPlayer("§cImpossivel entrar aguarde um pouco!\nEsta conta ja conectou recentemente!");
				return;
			}
			try {
				GeoLocation gl = new GeoLocation(ip);
				String countryCode = gl.getCountryCode();
				String stateString = gl.getState();
				if (Data.countryList.contains(countryCode)) {
					State state = State.getState(stateString);
					if (state != null) {
						if (state.getAllowed()) {
							state.Enter();
						} else {
							p.kickPlayer("§cAguarde " + state.getTime() + "s \nPara poder entrar!");
							return;
						}
					} else {
						p.kickPlayer("§cImpossivel entrar!\nContate: www.redeloock.com/discord");
						return;
					}
				} else {
					if (Data.outrosPermitidos) {
						if (Data.podeEntrar < Data.podeEntrarMaximo) {
							++Data.podeEntrar;
							if (!reduzindoTempo)
								tempoReduzir();
						} else {
							if (!reduzindoTempo)
								tempoReduzir();
							p.kickPlayer("§cAguarde " + tempoParaEntrar + "s \nPara poder entrar!");
							return;
						}
					} else {
						p.kickPlayer("§cEste País não pode entrar!\nContate: www.redeloock.com/discord");
						return;
					}
				}
				Bukkit.getConsoleSender().sendMessage("Info: " + p.getName() + ": " + countryCode + ": " + stateString);
			} catch (Exception e1) {
				Bukkit.getConsoleSender()
						.sendMessage("Impossivel acessar dados de " + p.getName() + " avise o MonderDragon!");
			}
		}
	}

	public static void tempoReduzir() {
		if (!reduzindoTempo) {
			tempoParaEntrar = Data.tempoEntrarProximo;
			reduzindoTempo = true;
		}
		Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
			public void run() {
				if (tempoParaEntrar > 0) {
					tempoParaEntrar -= 1;
					tempoReduzir();
				} else {
					reduzindoTempo = false;
					Data.podeEntrar -= 1;
				}
			}
		}, 20);
	}

	public static void retirarIpRecente(String ip) {
		Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
			public void run() {
				Data.ipRecente.remove(ip);
			}
		}, 20 * Data.ipRecenteReconectar);
	}

	public static void retirarEsperarReconectar(UUID uuid) {
		Data.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Data.plugin, new Runnable() {
			public void run() {
				Data.esperarReconectar.remove(uuid);
			}
		}, 20 * Data.esperaReconectarTempo);
	}
}
