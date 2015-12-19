package com.MCLovesMy.Events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.MCLovesMy.InventoryFilled;

import de.inventivegames.util.tellraw.TellrawConverterLite;
import de.inventivegames.util.title.TitleManager;

public class MobKill implements Listener{
	
	private InventoryFilled plugin;
	public MobKill(InventoryFilled plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
            	Player killer = (Player)entityDamageByEntityEvent.getDamager();
                UUID uuid = killer.getUniqueId();
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
        		if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if(plugin.config.getBoolean("Chat-Alert")) {
    	                        killer.sendMessage(ChatColor.RED + plugin.messages.getString("Actions.MobKill.Chat-Alert-Message"));
    	                        return;
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
            }
		}
	}
	
	@EventHandler
	public void onEntityDeath1(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
            	Player killer = (Player)entityDamageByEntityEvent.getDamager();
                UUID uuid = killer.getUniqueId();
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
            	if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if (plugin.config.getBoolean("Title-Alert")) {
    	                    		TitleManager.sendTimings(killer, 5, 30, 15);
    	                    		String raw2 = TellrawConverterLite.convertToJSON(ChatColor.RED + plugin.messages.getString("Actions.MobKill.Title-Alert-Message"));
    	                    		TitleManager.sendTitle(killer, raw2);
    	                    		String raw1 = TellrawConverterLite.convertToJSON(ChatColor.BLUE + plugin.messages.getString("Actions.MobKill.SubTitle-Alert-Message"));
    	                    		TitleManager.sendSubTitle(killer, raw1);
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
            }
		}
	}
	
	@EventHandler
	public void onEntityDeath2(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
            	Player killer = (Player)entityDamageByEntityEvent.getDamager();
                UUID uuid = killer.getUniqueId();
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
            	if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if (plugin.config.getBoolean("Sound-Alert")) {
    	                    		String sound = plugin.config.getString("Sound-Alert-Sound");
    	                    		Location loc = killer.getLocation();
    	                    		killer.getWorld().playSound(loc,Sound.valueOf(sound),1, 0);  
    	                    		return;
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
            }
		}
	}
}
