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
import com.MCLovesMy.Events.PlayerData.Join;
import com.MCLovesMy.Updaters.Updater;

	public class InventoryFilled extends JavaPlugin implements CommandExecutor{
	public File configFile = new File(getDataFolder()+"/config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public File messagesFile = new File(getDataFolder()+"/messages.yml");
    public FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
    public File playerdataFile = new File(getDataFolder()+"/playerdata.yml");
    public FileConfiguration playerdata = YamlConfiguration.loadConfiguration(playerdataFile);
    
    public Server server = Bukkit.getServer();
    public ConsoleCommandSender console = server.getConsoleSender();
    
    PluginDescriptionFile pdf = this.getDescription();
    
	
	public void onEnable() {
		getCommand("inventoryfilled").setExecutor(new MainCommand(this));
		getCommand("if").setExecutor(new MainCommand(this));
		registerEvents(this, new BlockBreak(this), new MobKill(this), new Join(this));
		
		loadConfiguration();
		saveConfigFile();
		saveMessagesFile();
		if (!playerdataFile.exists()) {
		savePlayerDataFile();
		}
		loadYamls();
	    
        getServer().getScheduler().runTaskLaterAsynchronously(this, new Runnable() {
            public void run() {
            	console.sendMessage(ChatColor.BLUE + "============================================");
                checkUpdateConsole();
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
		//config.yml
		config.options().header(
				"InventoryFilled Config!\n"
				+ "Explanation about all the options!\n"
				+ "DO NOT EDIT LINES WITH A # IN FRONT! THIS WILL NOT CHANGE SETTINGS! \n"
				+ "\n"
				+ "Turn the Chat-Alert on or off (true/false):\n"
				+ "Chat-Alert: true\n\n"
				+ "Turn the Title-Alert on or off (true/false):\n"
				+ "Title-Alert: true\n\n"
				+ "Turn the Sound-Alert on or off(true/false\n"
				+ "Sound-Alert: true\n\n"
				+ "Players can turn alerts on or off with /if on and /if off\n"
				+ "This Default-Alert-State option changes if players will get alerts by default, so when they did not turn it on/off yet.\n"
				+ "If true, players will get alerts by default. (So they have to do /if off if they don't want alerts)\n"
				+ "If false, players won't get alerts by default. (So they have to do /if on if the want alerts)\n"
				+ "Default-Alert-State: true"
				+ "\n\n"
				+ "Change which sound will be played when a full inventory.\n"
				+ "For sounds see: http://jd.bukkit.org/org/bukkit/Sound.html\n"
				+ "Sound-Alert-Sound: BLAZE_HIT"
				+ "\n\n");
		if (!config.contains("Chat-Alert")) {
			config.set("Chat-Alert", true);
		}
		if (!config.contains("Title-Alert")) {
			config.set("Title-Alert", true);
		}
		if (!config.contains("Sound-Alert")) {
			config.set("Sound-Alert", true);
		}
		if (!config.contains("Default-Alert-State")) {
			config.set("Default-Alert-State", true);
		}
		if (!config.contains("Sound-Alert-Sound")) {
			config.set("Sound-Alert-Sound", "BLAZE_HIT");
		}
		config.options().copyDefaults(true).copyHeader(true);
		//messages.yml
		messages.addDefault("Actions.BlockBreak.Chat-Alert-Message", "You can't pick this block up, your inventory is full!");
		messages.addDefault("Actions.BlockBreak.Title-Alert-Message", "Inventory Full");
		messages.addDefault("Actions.BlockBreak.SubTitle-Alert-Message", "You can't pick this block up");
		messages.addDefault("Actions.MobKill.Chat-Alert-Message", "You can't pick the mob drops up, your inventory is full!");
		messages.addDefault("Actions.MobKill.Title-Alert-Message", "Inventory Full");
		messages.addDefault("Actions.MobKill.SubTitle-Alert-Message", "You can't pick the mob drops up!");
		messages.options().copyDefaults(true).copyHeader(true);
		//PlayerData
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
	
	public void savePlayerDataFile() {
	    try {
	        playerdata.save(playerdataFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadYamls() {
	    try {
	        config.load(configFile);
	        messages.load(messagesFile);
	        playerdata.load(playerdataFile);;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	
    	public void checkUpdateConsole() {
    		console.sendMessage(ChatColor.DARK_AQUA + "Checking for InventoryFilled updates...");
            final Updater updater = new Updater(this, 14072, false);
            final Updater.UpdateResult result = updater.getResult();
            switch (result) {
                case FAIL_SPIGOT: {
                	console.sendMessage(ChatColor.RED + "ERROR: The updater of InventoryFilled could not contact Spgitomc.org");
                    break;
                }
                case NO_UPDATE: {
                	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFilled updater works fine!");
                	console.sendMessage(ChatColor.GREEN + "You have the latest InventoryFilled version!");
                	console.sendMessage(ChatColor.DARK_AQUA + "Current version: " + pdf.getVersion());
                    break;
                }
                case UPDATE_AVAILABLE: {
                    String version = updater.getVersion();
                	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFilled updater works fine!");
                    console.sendMessage(ChatColor.GREEN + "An InventoryFilled update is found!");
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
