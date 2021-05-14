package io.github.seggan.slimefunwarfare.listeners;

import com.google.common.collect.Sets;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.items.powersuits.Module;
import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class ModuleListener implements Listener {

    @EventHandler
    public void onLand(@Nonnull EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            ItemStack boots = p.getInventory().getBoots();
            Util.ifPowerSuit(boots, suit -> {
                if (Sets.newHashSet(PowerSuit.getModules(boots)).contains(Module.NANOFIBER_CUSHION) &&
                    suit.getItemCharge(boots) >= Module.NANOFIBER_CUSHION.getPower()) {
                    suit.removeItemCharge(boots, Module.NANOFIBER_CUSHION.getPower());
                    e.setCancelled(true);
                }
            });
        }
    }

    @EventHandler
    public void onPlayerSprint(@Nonnull PlayerToggleSprintEvent e) {
        Player p = e.getPlayer();
        ItemStack leggings = p.getInventory().getLeggings();
        Util.ifPowerSuit(leggings, suit -> {
            if (Sets.newHashSet(PowerSuit.getModules(leggings)).contains(Module.REACTION_WHEELS)) {
                if (e.isSprinting()) {
                    if (suit.getItemCharge(leggings) >= Module.REACTION_WHEELS.getPower()) {
                        p.addPotionEffect(new PotionEffect(
                            PotionEffectType.SPEED,
                            Integer.MAX_VALUE,
                            2,
                            false,
                            false,
                            false
                        ));
                        suit.removeItemCharge(leggings, Module.REACTION_WHEELS.getPower());
                    }
                } else {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
            }
        });
    }

    @EventHandler
    public void onPlayerDamageByEntity(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Enderman && e.getDamager() instanceof Player) {
            ((Enderman) e.getEntity()).setTarget((Player) e.getDamager());
        }
    }

    @EventHandler
    public void onEndermanTarget(@Nonnull EntityTargetLivingEntityEvent e) {
        if (e.getEntity().getType() == EntityType.ENDERMAN && e.getReason() == EntityTargetEvent.TargetReason.CLOSEST_PLAYER) {
            Util.ifPowerSuit(((Player) e.getTarget()).getInventory().getHelmet(), suit -> e.setCancelled(true));
        }
    }
}
