package com.gmail.jackdonofrio99;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NewPlayerJoinListener implements Listener {

	private JavaPlugin plugin;

	public NewPlayerJoinListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onNewPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
//		String playerUUIDString = player.getUniqueId().toString();
//		FileConfiguration config = plugin.getConfig();
//		
//		config.set(playerUUIDString + ".stats.stone_mined", 0);
//		config.set(playerUUIDString + ".stats.exp", 0);
//		plugin.saveConfig();

		if (!player.hasPlayedBefore()) {
			givePlayerStarterKit(player);
		}
	}

	public void givePlayerStarterKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.BREAD, 8));
		player.getInventory().addItem(new ItemStack(Material.WOODEN_PICKAXE, 1));
		player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD, 1));
		player.getInventory().addItem(new ItemStack(Material.WOODEN_AXE, 1));
		player.getInventory().addItem(new ItemStack(Material.WOODEN_SHOVEL, 1));
	}

}
