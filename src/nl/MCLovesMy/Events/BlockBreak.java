package nl.MCLovesMy.Events;
 
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.inventivegames.util.tellraw.TellrawConverterLite;
import de.inventivegames.util.title.TitleManager;
import nl.MCLovesMy.Main;

public class BlockBreak implements Listener{
	
	private Main plugin;
	public BlockBreak(Main plugin) {
		this.plugin = plugin;
	}
	
		//BlockBreak event
		@EventHandler
	    public void BlockBreakEvent(BlockBreakEvent e) {
	        if (e.getPlayer().getInventory().firstEmpty() == -1){
	            for(ItemStack item : e.getBlock().getDrops()){
	                for (int i=0; i<35; i++) {
	                    if (e.getPlayer().getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
	                        if (e.getPlayer().getInventory().getItem(i).getType().equals(item.getType())) {
	                       
	                            break;
	                        }
	                    }
	                    if (i==34) {
	                    	if(plugin.getConfig().getBoolean("Chat-warning")) 
	                        e.getPlayer().sendMessage(ChatColor.RED + "You can't pickup " + item.getType() + ChatColor.RED + ", your inventory is full!");
	                    	if (plugin.getConfig().getBoolean("Title-warning")) {
	                    		TitleManager.sendTimings(e.getPlayer(), 5, 30, 15);
	                    		TitleManager.sendTitle(e.getPlayer(), "{\"text\":\"\",\"extra\":[{\"text\":\"Inventory Full\",\"color\":\"red\"}]}");
	                    		String raw1 = TellrawConverterLite.convertToJSON(ChatColor.BLUE + "You can't pickup " + item.getType());
	                    		TitleManager.sendSubTitle(e.getPlayer(), raw1);
	                    	} else {
	                    		return;
	                    	}
	                    }
	                }
	            }
	        }
	    }

}
