package nl.MCLovesMy;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.MCLovesMy.Commands.MainCommand;
import nl.MCLovesMy.Events.BlockBreak;

public class Main extends JavaPlugin implements Listener, CommandExecutor{
	
	public void onEnable() {
		getCommand("inventoryfilled").setExecutor(new MainCommand(this));
		getCommand("if").setExecutor(new MainCommand(this));
		getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
	}
}
	
	
	    

