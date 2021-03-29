package io.github.seggan.slimefunwarfare.listeners;

import com.google.common.collect.Sets;
import io.github.seggan.slimefunwarfare.items.powersuits.Module;
import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ModuleListener implements Listener {

    @EventHandler
    public void onLand(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            ItemStack boots = p.getInventory().getBoots();
            SlimefunItem item = SlimefunItem.getByItem(boots);
            if (item instanceof PowerSuit) {
                PowerSuit suit = (PowerSuit) item;
                if (Sets.newHashSet(PowerSuit.getModules(boots)).contains(Module.NANOFIBER_CUSHION) &&
                    suit.getItemCharge(boots) >= 5) {
                    suit.removeItemCharge(boots, 5);
                    e.setCancelled(true);
                }
            }
        }
    }
}
