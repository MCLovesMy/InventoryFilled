package nl.MCLovesMy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Main {
	//Main Command
	@Override
	public boolean oncommand (CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("InventoryFilled")) {
			
			return true;
		}
		
	}

}
