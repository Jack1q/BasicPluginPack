package com.gmail.jackdonofrio99;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.jackdonofrio99.chat.ChatListener;
import com.gmail.jackdonofrio99.chat.MsgCommand;
import com.gmail.jackdonofrio99.chat.MutePlayerCommand;
import com.gmail.jackdonofrio99.chat.ReplyCommand;
import com.gmail.jackdonofrio99.chat.UnMutePlayerCommand;
import com.gmail.jackdonofrio99.teleports.HomeCommand;
import com.gmail.jackdonofrio99.teleports.SetHomeCommand;
import com.gmail.jackdonofrio99.teleports.SetSpawnCommand;
import com.gmail.jackdonofrio99.teleports.SpawnCommand;
import com.gmail.jackdonofrio99.teleports.WhereHomeCommand;

public class BasicPluginPack extends JavaPlugin {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getCommand("joindate").setExecutor(new JoinDateCommand());
		this.getCommand("lastseen").setExecutor(new LastSeenCommand(this));

		this.getCommand("sethome").setExecutor(new SetHomeCommand(this));
		this.getCommand("home").setExecutor(new HomeCommand(this));
		this.getCommand("wherehome").setExecutor(new WhereHomeCommand(this));
		this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		this.getCommand("spawn").setExecutor(new SpawnCommand(this));

		this.getCommand("mute").setExecutor(new MutePlayerCommand(this));
		this.getCommand("unmute").setExecutor(new UnMutePlayerCommand(this));
		this.getCommand("msg").setExecutor(new MsgCommand(this));
		this.getCommand("reply").setExecutor(new ReplyCommand(this));

		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		getServer().getPluginManager().registerEvents(new NewPlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerLogOutListener(this), this);
	}

	@Override
	public void onDisable() {

	}

}
