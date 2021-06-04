package com.gmail.jackdonofrio99;

import org.bukkit.plugin.java.JavaPlugin;

public class BasicPluginPack extends JavaPlugin {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getCommand("joindate").setExecutor(new JoinDateCommand());

		this.getCommand("sethome").setExecutor(new SetHomeCommand(this));
		this.getCommand("home").setExecutor(new HomeCommand(this));
		this.getCommand("gethome").setExecutor(new GetHomeCommand(this));

		this.getCommand("mute").setExecutor(new MutePlayerCommand(this));
		this.getCommand("unmute").setExecutor(new UnMutePlayerCommand(this));

		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		getServer().getPluginManager().registerEvents(new NewPlayerJoinListener(this), this);
	}

	@Override
	public void onDisable() {

	}

}
