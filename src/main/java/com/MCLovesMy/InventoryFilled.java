package com.MCLovesMy;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.MCLovesMy.Commands.MainCommand;
import com.MCLovesMy.Events.BlockBreak;
import com.MCLovesMy.Events.MobKill;

	public class InventoryFilled extends JavaPlugin implements CommandExecutor{
	public File configFile = new File(getDataFolder()+"/config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public File messagesFile = new File(getDataFolder()+"/messages.yml");
    public FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
    
  
    Server server = Bukkit.getServer();
    ConsoleCommandSender console = server.getConsoleSender();
    
    PluginDescriptionFile pdf = this.getDescription();
	
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
	    
        getServer().getScheduler().runTaskLaterAsynchronously(this, new Runnable() {
            public void run() {
            	console.sendMessage(ChatColor.BLUE + "============================================");
                checkUpdate();
        	    console.sendMessage(ChatColor.BLUE + "============================================");
            }
        }, 20L);
        
	    try {
	        MetricsLite metrics = new MetricsLite(this);
	        metrics.start();
	        console.sendMessage(ChatColor.DARK_AQUA + "InventoryFilled: Stats about your server are being send to MCStats.org!");
	    } catch (IOException e) {
	    	console.sendMessage(ChatColor.RED + "InventoryFilled ERROR: Stats bout your server couldn't be send to MCStats.org!");
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
	
	//Updater
	public void checkUpdate() {
		console.sendMessage(ChatColor.DARK_AQUA + "Checking for InventoryFull updates...");
        final Updater updater = new Updater(this, 14072, false);
        final Updater.UpdateResult result = updater.getResult();
        switch (result) {
            case FAIL_SPIGOT: {
            	console.sendMessage(ChatColor.RED + "ERROR: The updater of InventoryFilled could not contact Spgitomc.org");
                break;
            }
            case NO_UPDATE: {
            	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFull updater works fine!");
            	console.sendMessage(ChatColor.GREEN + "You have the latest InventoryFull version!");
            	console.sendMessage(ChatColor.DARK_AQUA + "Current version: " + pdf.getVersion());
                break;
            }
            case UPDATE_AVAILABLE: {
                String version = updater.getVersion();
            	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFull updater works fine!");
                console.sendMessage(ChatColor.GREEN + "An InventoryFull update is found!");
                console.sendMessage(ChatColor.DARK_AQUA + "Your version: " + pdf.getVersion() + ". Newest Version: " + version);
                @SuppressWarnings("unused")
				Boolean updateAvailable = true;
                break;
            }
            default: {
                console.sendMessage(result.toString());
                break;
            }
        }
    }
}
