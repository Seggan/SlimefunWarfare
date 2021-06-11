package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.lists.Items;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.inventory.ItemStack;

public class PyroListener implements Listener {

    @EventHandler
    public void onPyroPowderCombust(EntityCombustByBlockEvent e) {
        if (e.getEntityType() != EntityType.DROPPED_ITEM) {
            return;
        }
        Entity entity = e.getEntity();
        ItemStack item = ((Item) entity).getItemStack();
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) {
            return;
        }
        if (sfItem.getItem().equals(Items.PYRO_POWDER)) {
            entity.getWorld().createExplosion(entity.getLocation(), 3F);
        }
    }
}
