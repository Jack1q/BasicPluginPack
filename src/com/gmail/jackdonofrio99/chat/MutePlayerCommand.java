package com.gmail.jackdonofrio99.chat;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.jackdonofrio99.NameToUUID;

import net.md_5.bungee.api.ChatColor;

public class MutePlayerCommand implements CommandExecutor {

	private JavaPlugin plugin;

	public MutePlayerCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + "Proper usage: /mute (username)");
			return true;
		}
		String username = args[0];
		String playerUUID;
		try {
			playerUUID = NameToUUID.getUUID(username);
		} catch (IOException e) {
			e.printStackTrace();
			sender.sendMessage(ChatColor.RED + "Could not find player " + username);
			return true;
		}
		FileConfiguration config = plugin.getConfig();
		config.set(playerUUID + ".isMuted", true);
		plugin.saveConfig();
		sender.sendMessage(ChatColor.AQUA + username + " has been muted.");
		return true;
	}

}
