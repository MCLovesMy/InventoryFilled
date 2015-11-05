package nl.MCLovesMy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import nl.MCLovesMy.Main;

public class MainCommand implements CommandExecutor{
	
	private Main plugin;
	public MainCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	//Main Command
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("inventoryFilled")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				p.sendMessage(ChatColor.BLUE + " - /InventoryFilled reload");
				p.sendMessage(ChatColor.BLUE + " - /if reload");
				return true;
			} else {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.loadYamls();
				p.sendMessage(ChatColor.GREEN + "InventoryFilled files succesfully reloaded!");
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "That does not exist!");
				p.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				p.sendMessage(ChatColor.BLUE + " - /InventoryFilled reload");
				p.sendMessage(ChatColor.BLUE + " - /if reload");
				return true;
			}
		}
		}
		else if (cmd.getName().equalsIgnoreCase("if")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				p.sendMessage(ChatColor.BLUE + " - /InventoryFilled reload");
				p.sendMessage(ChatColor.BLUE + " - /if reload");
				return true;
		} else {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.loadYamls();
				p.sendMessage(ChatColor.GREEN + "InventoryFilled files succesfully reloaded!");
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "That does not exist!");
				p.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				p.sendMessage(ChatColor.BLUE + " - /InventoryFilled reload");
				p.sendMessage(ChatColor.BLUE + " - /if reload");
				return true;
			}
			} 
		} return true;
		
	}
}
