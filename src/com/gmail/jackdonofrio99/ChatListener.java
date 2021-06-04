package com.gmail.jackdonofrio99;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener implements Listener {

	private JavaPlugin plugin;

	public ChatListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onChatMessageSend(AsyncPlayerChatEvent event) throws IOException {
		Player player = event.getPlayer();
		if (checkIfMuted(player)) {
			player.sendMessage(ChatColor.RED + "Sorry, you are muted.");
			event.setCancelled(true);
			return;
		}
		String message = event.getMessage();
		message = filterMessage(message);
		event.setFormat(ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + message);
	}

	public boolean checkIfMuted(Player player) {
		FileConfiguration config = plugin.getConfig();
		String playerUUID = player.getUniqueId().toString();
		String isMutedPath = playerUUID + ".isMuted";
		if (config.contains(isMutedPath) && config.getBoolean(isMutedPath)) {
			return true;
		}
		return false;
	}

	public String filterMessage(String message) throws IOException {
		InputStream is = plugin.getResource("badwords.txt");
		Scanner scanner = new Scanner(is);
		while (scanner.hasNextLine()) {
			String badWord = scanner.nextLine().trim();
			if (message.toLowerCase().contains(badWord.toLowerCase())) {
				message = message.replaceAll("(?i)" + badWord,
						new String(new char[badWord.length()]).replace('\0', '*'));
			}
		}
		scanner.close();
		return message;

	}

}
