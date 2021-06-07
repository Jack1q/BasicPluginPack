package com.gmail.jackdonofrio99.teleports;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class SetHomeCommand implements CommandExecutor {

	private JavaPlugin plugin;

	public SetHomeCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Location l = ((Player) sender).getLocation();
		String playerUUID = ((Player) sender).getUniqueId().toString();
		FileConfiguration config = plugin.getConfig();
		config.set(playerUUID + ".home.location.world", l.getWorld().getName());
		config.set(playerUUID + ".home.location.x", l.getX());
		config.set(playerUUID + ".home.location.y", l.getY());
		config.set(playerUUID + ".home.location.z", l.getZ());
		plugin.saveConfig();
		sender.sendMessage(ChatColor.AQUA + "Home set.");
		return true;
	}

}

