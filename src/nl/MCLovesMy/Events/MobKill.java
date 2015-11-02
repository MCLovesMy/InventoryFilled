package nl.MCLovesMy.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.inventivegames.util.tellraw.TellrawConverterLite;
import de.inventivegames.util.title.TitleManager;
import nl.MCLovesMy.Main;

public class MobKill implements Listener{
	
	private Main plugin;
	public MobKill(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
                Player killer = (Player)entityDamageByEntityEvent.getDamager();
                if (killer.getInventory().firstEmpty() != -1){
    	            for(ItemStack item : e.getDrops()){
    	                return;
    	            }
    	   
    	        } else {
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if(plugin.getConfig().getBoolean("Chat-warning")) {
    	                        killer.sendMessage(ChatColor.RED + "You can't pickup the drops from " + entity.getName() + ChatColor.RED + ", your inventory is full!");
    	                    	}
    	                    	if (plugin.getConfig().getBoolean("Title-warning")) {
    	                    		TitleManager.sendTimings(killer, 5, 30, 15);
    	                    		TitleManager.sendTitle(killer, "{\"text\":\"\",\"extra\":[{\"text\":\"Inventory Full\",\"color\":\"red\"}]}");
    	                    		String raw1 = TellrawConverterLite.convertToJSON(ChatColor.BLUE + "You can't pickup the drops from " + entity.getName());
    	                    		TitleManager.sendSubTitle(killer, raw1);
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
