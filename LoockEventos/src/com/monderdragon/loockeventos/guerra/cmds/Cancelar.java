package com.monderdragon.loockeventos.guerra.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.monderdragon.loockeventos.guerra.Guerra;

public class Cancelar {

	public static void executar(CommandSender sender, Command cmd, String label, String[] args) {
		Guerra.Cancelar(sender);
	}

}
