package io.github.seggan.slimefunwarfare.listeners;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GrenadeListener implements Listener {

    @EventHandler
    public void onGrenadeHit(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();
        if (projectile.getType() != EntityType.SNOWBALL) {
            return;
        }

        if (projectile.hasMetadata("effect")) {
            String id = projectile.getMetadata("effect").get(0).asString();
            try {
                Location loc = e.getHitBlock().getRelative(e.getHitBlockFace()).getLocation();
                applyEffect(id, projectile, loc);
            } catch (NullPointerException ignored) {}
        }
    }

    @EventHandler
    public void onGrenadeHitEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (entity.getType() == EntityType.SNOWBALL) {
            if (entity.hasMetadata("effect")) {
                String id = entity.getMetadata("effect").get(0).asString();
                try {
                    Location loc = e.getEntity().getLocation();
                    applyEffect(id, entity, loc);
                } catch (NullPointerException ignored) {}
            }
        }
    }

    private void applyEffect(String id, Entity p, Location loc) {
        switch (id) {
            case "NITROGEN_TRIIODIDE":
                p.getWorld().createExplosion(loc, 2F, false, false);
                AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld()
                    .spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                cloud.addCustomEffect(new PotionEffect(
                    PotionEffectType.BLINDNESS,
                    100,
                    2,
                    false,
                    false
                ), true);
                cloud.setDuration(100);
                cloud.setDurationOnUse(0);
                cloud.setRadiusOnUse(0);
                cloud.setColor(Color.PURPLE);
                cloud.setRadius(3);
                break;
            case "AZIDOAZIDE_AZIDE":
                p.getWorld().createExplosion(loc, 7F);
                break;
        }
    }
}
