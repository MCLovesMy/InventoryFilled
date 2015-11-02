package nl.MCLovesMy;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.MCLovesMy.Commands.MainCommand;
import nl.MCLovesMy.Events.BlockBreak;
import nl.MCLovesMy.Events.MobKill;

public class Main extends JavaPlugin implements Listener, CommandExecutor{
	
	public void onEnable() {
		getCommand("inventoryfilled").setExecutor(new MainCommand(this));
		getCommand("if").setExecutor(new MainCommand(this));
		registerEvents(this, new BlockBreak(this), new MobKill(this));
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
		}
	
}
	
	
	    

