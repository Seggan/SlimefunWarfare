package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffectType;

public class BulletListener implements Listener {

    @EventHandler
    public void onEntityBulletHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Projectile)) return;

        Projectile bullet = (Projectile) e.getDamager();
        Entity shot = e.getEntity();
        if (bullet.hasMetadata("isGunBullet")) {
            if (bullet.getShooter() instanceof Player) {
                Player shooter = (Player) bullet.getShooter();
                if (!Slimefun.getProtectionManager().hasPermission(shooter, shot.getLocation(), Interaction.ATTACK_PLAYER)) {
                    return;
                }
            }
            Location shooterLoc = Util.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());
            String[] split = CommonPatterns.COLON.split(bullet.getMetadata("rangeInfo").get(0).asString());
            double distance = shooterLoc.distance(e.getEntity().getLocation());
            if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
                e.setDamage(bullet.getMetadata("damage").get(0).asInt());
                if (bullet.getMetadata("isFire").get(0).asBoolean()) {
                    shot.setFireTicks(e.getEntity().getFireTicks() + 60);
                }

                if (bullet instanceof ShulkerBullet && shot instanceof LivingEntity) {
                    Bukkit.getScheduler().runTaskLater(SlimefunWarfare.inst(), () -> ((LivingEntity) shot).removePotionEffect(PotionEffectType.LEVITATION), 1);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBulletHitBlock(ProjectileHitEvent e) {
        Block b = e.getHitBlock();
        Entity entity = e.getEntity();

        if (!(entity instanceof ShulkerBullet) || b == null) return;

        if (e.getEntity().hasMetadata("isGunBullet") && SlimefunWarfare.inst().getConfig().getBoolean("guns.energy-rifle-explosions", false)) {
            b.getWorld().createExplosion(b.getLocation(), 1F);
        }
    }
}
