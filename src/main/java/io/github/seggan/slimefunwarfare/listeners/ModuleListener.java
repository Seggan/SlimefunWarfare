package io.github.seggan.slimefunwarfare.listeners;

import com.google.common.collect.Sets;
import io.github.mooy1.infinitylib.core.ConfigUtils;
import io.github.mooy1.infinitylib.items.StackUtils;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.items.powersuits.Module;
import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import io.github.seggan.slimefunwarfare.lists.items.Guns;
import io.github.seggan.slimefunwarfare.lists.items.Melee;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
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
import org.bukkit.projectiles.ProjectileSource;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ModuleListener implements Listener {

    private static final Set<DamageCause> kinetic = EnumSet.of(
        DamageCause.CONTACT,
        DamageCause.THORNS,
        DamageCause.FALLING_BLOCK,
        DamageCause.FLY_INTO_WALL,
        DamageCause.CRAMMING
    );

    private static final Set<String> energyWeapons = Sets.newHashSet(
        Guns.ENERGY_RIFLE.getItemId(),
        Melee.ENERGY_BLADE.getItemId(),
        "NANO_BLADE",
        SlimefunItems.SWORD_OF_BEHEADING.getItemId(),
        SlimefunItems.SEISMIC_AXE.getItemId()
    );

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
    public void onPlayerDamage(@Nonnull EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            ItemStack chestplate = p.getInventory().getChestplate();
            Util.ifPowerSuit(chestplate, suit -> {
                if (ThreadLocalRandom.current().nextInt(100) < ConfigUtils.getInt("suits.energy-shield-chance", 0, 100, 90) &&
                    kinetic.contains(e.getCause()) && Sets.newHashSet(PowerSuit.getModules(chestplate)).contains(Module.ENERGY_SHIELD)) {
                    e.setCancelled(true);
                    suit.removeItemCharge(chestplate, Module.ENERGY_SHIELD.getPower());
                }
            });
        }
    }

    @EventHandler
    public void onPlayerDamageByEntity(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            ItemStack chestplate = p.getInventory().getChestplate();
            Util.ifPowerSuit(chestplate, suit -> {
                if (ThreadLocalRandom.current().nextInt(100) < ConfigUtils.getInt("suits.energy-shield-chance", 0, 100, 90) &&
                    Sets.newHashSet(PowerSuit.getModules(chestplate)).contains(Module.ENERGY_SHIELD)) {
                    if (e.getDamager() instanceof Player) {
                        ItemStack stack = ((Player) e.getDamager()).getInventory().getItemInMainHand();
                        if (energyWeapons.contains(StackUtils.getID(stack))) {
                            e.setCancelled(true);
                        }
                        if (!(stack.getType() == Material.TRIDENT && stack.getEnchantments().isEmpty())) {
                            e.setCancelled(true);
                        }
                    } else if (e.getDamager() instanceof Projectile) {
                        ProjectileSource shooter = ((Projectile) e.getDamager()).getShooter();
                        if (shooter instanceof Player) {
                            ItemStack stack = ((Player) shooter).getInventory().getItemInMainHand();
                            if (energyWeapons.contains(StackUtils.getID(stack))) {
                                e.setCancelled(true);
                            }
                            if (!(stack.getType() == Material.TRIDENT && !stack.getEnchantments().isEmpty())) {
                                e.setCancelled(true);
                            }
                        }
                    } else {
                        e.setCancelled(true);
                    }

                    if (e.isCancelled()) {
                        suit.removeItemCharge(chestplate, Module.ENERGY_SHIELD.getPower());
                    }
                }
            });
        } else if (e.getEntity() instanceof Enderman && e.getDamager() instanceof Player) {
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
