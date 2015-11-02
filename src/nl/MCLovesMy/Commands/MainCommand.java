package nl.MCLovesMy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import nl.MCLovesMy.Main;

public class MainCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main main;
	public MainCommand(Main main) {
		this.main = main;
	}
	
	//Main Command
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("inventoryFilled")) {
			p.sendMessage(ChatColor.BLUE + "InventoryFilled has no commands! Just fill your inventory and see!");
		}
		else if (cmd.getName().equalsIgnoreCase("if")) {
			p.sendMessage(ChatColor.BLUE + "InventoryFilled has no commands! Just fill your inventory and see!");
		}
		return true;
	}
	
}
