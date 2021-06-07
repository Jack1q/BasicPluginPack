package com.gmail.jackdonofrio99;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLogOutListener implements Listener {

	private JavaPlugin plugin;

	public PlayerLogOutListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLogOut(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		Date currentTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.US);
		config.set(player.getUniqueId().toString() + ".lastseen", format.format(currentTime) + " EST");
		plugin.saveConfig();
	}

}
