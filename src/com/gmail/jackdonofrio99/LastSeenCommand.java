package com.gmail.jackdonofrio99;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LastSeenCommand implements CommandExecutor {

	private JavaPlugin plugin;

	public LastSeenCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.DARK_AQUA + "Please enter a username.");
			return true;
		}
		String username = args[0];
		Player player = Bukkit.getPlayerExact(username);
		if (player == null) {
			FileConfiguration config = plugin.getConfig();
			String playerUUID;
			try {
				playerUUID = NameToUUID.getUUID(username);
			} catch (IOException | NullPointerException e) {
				sender.sendMessage(ChatColor.RED + "Could not find player " + username);
				return true;
			}

			if (config.contains(playerUUID)) {
				String lastJoined = config.getString(playerUUID + ".lastseen");
				sender.sendMessage(ChatColor.AQUA + "" + username + ChatColor.DARK_AQUA + " last seen on "
						+ ChatColor.AQUA + lastJoined);
			} else {
				sender.sendMessage(ChatColor.RED + "" + username + " has never joined.");
			}

		} else {
			sender.sendMessage(ChatColor.AQUA + "" + username + " is currently online!");
		}

		return true;
	}

}
