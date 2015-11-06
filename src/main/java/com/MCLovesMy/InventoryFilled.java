package com.MCLovesMy;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.MCLovesMy.Commands.MainCommand;
import com.MCLovesMy.Events.BlockBreak;
import com.MCLovesMy.Events.MobKill;

public class InventoryFilled extends JavaPlugin implements CommandExecutor{
	public File configFile = new File(getDataFolder()+"/config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public File messagesFile = new File(getDataFolder()+"/messages.yml");
    public FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
	
	public void onEnable() {
		getCommand("inventoryfilled").setExecutor(new MainCommand(this));
		getCommand("if").setExecutor(new MainCommand(this));
		registerEvents(this, new BlockBreak(this), new MobKill(this));
		
		loadConfiguration();
		if (!configFile.exists()) {
		saveConfigFile();
		}
		loadConfiguration();
		if (!messagesFile.exists()) {
		saveMessagesFile();
		}
		loadYamls();
		
	    try {
	        MetricsLite metrics = new MetricsLite(this);
	        metrics.start();
	    } catch (IOException e) {
	        // Failed to submit the stats :-(
	    }
		
	}
	
	public void loadConfiguration() {
		config.addDefault("Chat-Alert", true);
		config.addDefault("Title-Alert", true);
		config.addDefault("Sound-Alert", true);
		config.options().copyDefaults(true);
		//Custom messages
		messages.addDefault("Actions.BlockBreak.Chat-Alert-Message", "You can't pick this block up, your inventory is full!");
		messages.addDefault("Actions.BlockBreak.Title-Alert-Message", "Inventory Full");
		messages.addDefault("Actions.BlockBreak.SubTitle-Alert-Message", "You can't pick this block up");
		messages.addDefault("Actions.MobKill.Chat-Alert-Message", "You can't pick the mob drops up, your inventory is full!");
		messages.addDefault("Actions.MobKill.Title-Alert-Message", "Inventory Full");
		messages.addDefault("Actions.MobKill.SubTitle-Alert-Message", "You can't pick the mob drops up!");
		messages.options().copyDefaults(true);
	}
	
	public void saveConfigFile() {
	    try {
	        config.save(configFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveMessagesFile() {
	    try {
	        messages.save(messagesFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadYamls() {
	    try {
	        config.load(configFile);
	        messages.load(messagesFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
}
