package com.MCLovesMy.Events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.MCLovesMy.InventoryFilled;

import de.inventivegames.util.tellraw.TellrawConverterLite;
import de.inventivegames.util.title.TitleManager;

public class BlockBreak implements Listener{
	
	private InventoryFilled plugin;
	public BlockBreak(InventoryFilled plugin) {
		this.plugin = plugin;
	}
	
		//BlockBreak event
		@EventHandler
	    public void BlockBreakEvent(BlockBreakEvent e) {
			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			Location loc = p.getLocation();
			if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
	        if (p.getInventory().firstEmpty() == -1){
	            for(ItemStack item : e.getBlock().getDrops()){
	                for (int i=0; i<35; i++) {
	                    if (p.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
	                        if (p.getInventory().getItem(i).getType().equals(item.getType())) {
	                       
	                            break;
	                        }
	                    }
	                    if (i==34) {
	                    	if(plugin.config.getBoolean("Chat-Alert")) 
	                        p.sendMessage(ChatColor.RED + plugin.messages.getString("Actions.BlockBreak.Chat-Alert-Message"));
	                    	if (plugin.config.getBoolean("Title-Alert")) {
	                    		TitleManager.sendTimings(p, 5, 30, 15);
	                    		String raw2 = TellrawConverterLite.convertToJSON(ChatColor.RED + plugin.messages.getString("Actions.BlockBreak.Title-Alert-Message"));
	                    		TitleManager.sendTitle(p, raw2);
	                    		String raw1 = TellrawConverterLite.convertToJSON(ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.SubTitle-Alert-Message"));
	                    		TitleManager.sendSubTitle(p, raw1);
	                    		}
	                    	if (plugin.config.getBoolean("Sound-Alert")) {
	                    		String sound = plugin.config.getString("Sound-Alert-Sound");
	                    		p.getWorld().playSound(loc,Sound.valueOf(sound),1, 0);  
	                    		
	                    	} else {
	                    		return;
	                    	}
	                    }
	                }
	            }
	        }
	    }
	}
}
