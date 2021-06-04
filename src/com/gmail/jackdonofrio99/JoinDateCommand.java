package com.gmail.jackdonofrio99;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class JoinDateCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.DARK_AQUA + "Please enter a username.");
			return true;
		}
		String username = args[0];
		long joinDateMilliseconds;
		if (Bukkit.getPlayer(username) != null) {
			joinDateMilliseconds = Bukkit.getPlayer(username).getFirstPlayed();
		} else {
			// note: change to Bukkit.getOfflinePlayer(UUID uuid) after UUID converter is finished
			joinDateMilliseconds = Bukkit.getOfflinePlayer(username).getFirstPlayed();
		}
		if (joinDateMilliseconds != 0) {
			Date dateObject = new Date(joinDateMilliseconds);
			String formattedJoinDate = new SimpleDateFormat("MM-dd-yyyy", Locale.US).format(dateObject);
			String formattedJoinTime = new SimpleDateFormat("HH:mm", Locale.US).format(dateObject);
			sender.sendMessage(ChatColor.AQUA + username + ChatColor.DARK_AQUA + " joined on " + ChatColor.AQUA
					+ formattedJoinDate + ChatColor.DARK_AQUA + " at " + ChatColor.AQUA + formattedJoinTime + " EST"
					+ ChatColor.DARK_AQUA + ".");
		} else {
			sender.sendMessage(ChatColor.AQUA + username + ChatColor.DARK_AQUA + " has never joined.");
		}
		return true;
	}

}
