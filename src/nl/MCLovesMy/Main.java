package nl.MCLovesMy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{
	//Main Command
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("inventoryfull")) {
			p.sendMessage(ChatColor.BLUE + "InventoryFull has no commands! Just fill your inventory and see!");
		}
		else if (cmd.getName().equalsIgnoreCase("if")) {
			p.sendMessage(ChatColor.BLUE + "InventoryFull has no commands! Just fill your inventory and see!");
		}
		return false;
	}

		
}
