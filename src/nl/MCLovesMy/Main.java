package nl.MCLovesMy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor{
	
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.saveConfig();
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

	//Main code: Event + response
	@EventHandler
    public void BlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().firstEmpty() != -1){
            for(ItemStack item : e.getBlock().getDrops()){
                e.getPlayer().getInventory().addItem(item);
            }
   
        } else {
            for(ItemStack item : e.getBlock().getDrops()){
                for (int i=0; i<35; i++) {
                    if (e.getPlayer().getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
                        if (e.getPlayer().getInventory().getItem(i).getType().equals(item.getType())) {
                       
                            break;
                        }
                    }
                    if (i==34) {
                        e.getPlayer().sendMessage(ChatColor.RED + "You can't pickup " + item.getType() + ChatColor.RED + ", your inventory is full!");
                    }
                }
            }
        }
    }
}
	
	
	    

