package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.items.Dummy;
import io.github.seggan.slimefunwarfare.items.EnergyBlade;
import io.github.seggan.slimefunwarfare.lists.Items;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HitListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDummyHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;

        Entity entity = e.getEntity();
        if (PersistentDataAPI.getString(entity, Dummy.KEY) != null) {
            e.setCancelled(true);
            Player p = (Player) e.getDamager();
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColors.color(
                String.format("&cYou have dealt %d half-hearts of damage", Math.round(e.getFinalDamage()))
            )));
        }
    }

    @EventHandler
    public void onDummyDestroy(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (PersistentDataAPI.getString(entity, Dummy.KEY) != null) {
            entity.remove();
            ItemStack stack = Items.DUMMY.clone();
            stack.setAmount(1);
            entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
        }
    }

//    @EventHandler(priority = EventPriority.HIGH)
//    public void onHit(EntityDamageByEntityEvent e) {
//        Entity entity = e.getDamager();
//        if (!(entity instanceof Player)) return;
//
//        PlayerInventory inv = ((Player) entity).getInventory();
//        ItemStack mainHand = inv.getItemInMainHand();
//        ItemStack offHand = inv.getItemInOffHand();
//
//        SlimefunItem mainItem = SlimefunItem.getByItem(mainHand);
//        SlimefunItem offItem = SlimefunItem.getByItem(offHand);
//
//        if (mainItem instanceof EnergyBlade && offItem instanceof EnergyBlade) {
//            EnergyBlade blade = (EnergyBlade) mainItem;
//            float charge = blade.getItemCharge(mainHand);
//            float offCharge = blade.getItemCharge(offHand);
//
//            if (charge < 5 || offCharge < 5) {
//                e.setCancelled(true);
//                e.getEntity().setFireTicks(0);
//            } else {
//                blade.removeItemCharge(mainHand, 5);
//                blade.removeItemCharge(offHand, 5);
//            }
//        } else if (SlimefunUtils.isItemSimilar())
//    }
}
