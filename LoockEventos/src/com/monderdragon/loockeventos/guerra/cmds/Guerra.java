package com.monderdragon.loockeventos.guerra.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Guerra implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Guerra")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("Cancelar") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					Cancelar.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("Sair")) {
					Sair.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("Iniciar") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					Iniciar.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("Finalizar") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					Finalizar.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("Participar")) {
					Participar.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("SetarItens") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					SetarItens.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("SetarLocal") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					SetarLocal.executar(sender, cmd, label, args);
					return true;
				}
				if (args[0].equalsIgnoreCase("Setar") && sender.hasPermission("LoockEventos.Guerra.Controlar")) {
					Setar.executar(sender, cmd, label, args);
					return true;
				}
				if (!sender.hasPermission("LoockEventos.Guerra.Iniciar"))
					sender.sendMessage("§cComando invalido utilize: §f/Guerra Participar");
				else
					sender.sendMessage(
							"§cComando invalido utilize: §f/Guerra Participar,Iniciar,Finalizar,Cancelar,Setar");
			} else {
				if (!sender.hasPermission("LoockEventos.Guerra.Iniciar"))
					sender.sendMessage("§cComando invalido utilize: §f/Guerra Participar");
				else
					sender.sendMessage(
							"§cComando invalido utilize: §f/Guerra Participar,Iniciar,Finalizar,Cancelar,Setar");
			}
		}
		return false;
	}
}
