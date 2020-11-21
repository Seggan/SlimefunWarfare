package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.Util;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BulletListener implements Listener {

    @EventHandler
    public void onEntityBulletHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof LlamaSpit)) {
            return;
        }
        LlamaSpit bullet = (LlamaSpit) e.getDamager();
        Entity shot = e.getEntity();
        if (bullet.hasMetadata("isGunBullet")) {

            // Protection checks
            if (shot instanceof Player) {
                if (!SlimefunPlugin.getProtectionManager()
                    .hasPermission((Player) shot, shot.getLocation(), ProtectableAction.PVP)) {
                    return;
                }
            }

            Location shooterLoc = Util.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());
            String[] split = bullet.getMetadata("rangeInfo").get(0).asString().split(":");
            double distance = shooterLoc.distance(e.getEntity().getLocation());
            if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
                e.setDamage(bullet.getMetadata("damage").get(0).asInt());
                if (bullet.getMetadata("isFire").get(0).asBoolean()) {
                    e.getEntity().setFireTicks(e.getEntity().getFireTicks() + 60);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }
}
