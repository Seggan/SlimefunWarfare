package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.items.EnergyBlade;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HitListener implements Listener {

    @EventHandler
    public void onEnergyBladeHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (!(entity instanceof Player)) return;

        ItemStack item = ((Player) entity).getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem(item);
        if (slimefunItem instanceof EnergyBlade) {
            EnergyBlade blade = (EnergyBlade) slimefunItem;
            float charge = blade.getItemCharge(item);

            if (charge < 5) {
                e.setCancelled(true);
                e.getEntity().setFireTicks(0);
            } else {
                blade.removeItemCharge(item, 5);
            }
        }
    }
}
