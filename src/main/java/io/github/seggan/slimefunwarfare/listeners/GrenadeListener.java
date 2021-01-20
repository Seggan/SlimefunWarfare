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
            try {
                Location loc = e.getHitBlock().getRelative(e.getHitBlockFace()).getLocation();
                applyEffect(projectile, loc);
            } catch (NullPointerException ignored) {}
        }
    }

    @EventHandler
    public void onGrenadeHitEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (entity.getType() == EntityType.SNOWBALL) {
            if (entity.hasMetadata("effect")) {
                try {
                    Location loc = e.getEntity().getLocation();
                    applyEffect(entity, loc);
                } catch (NullPointerException ignored) {}
            }
        }
    }

    private void applyEffect(Entity snowball, Location loc) {
        String id = snowball.getMetadata("effect").get(0).asString();
        switch (id) {
            case "NITROGEN_TRIIODIDE":
                snowball.getWorld().createExplosion(loc, 3F, false, false);
                AreaEffectCloud cloud = (AreaEffectCloud) snowball.getWorld()
                    .spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                cloud.addCustomEffect(new PotionEffect(
                    PotionEffectType.BLINDNESS,
                    100,
                    3,
                    false,
                    false
                ), true);
                cloud.setDuration(100);
                cloud.setDurationOnUse(0);
                cloud.setRadiusOnUse(0);
                cloud.setColor(Color.PURPLE);
                cloud.setRadius(4);
                break;
            case "AZIDOAZIDE_AZIDE":
                snowball.getWorld().createExplosion(loc, 7F);
                break;
            case "ARSENIC":
                snowball.getWorld().createExplosion(loc, 1F, false, false);
                AreaEffectCloud cloud1 = (AreaEffectCloud) snowball.getWorld()
                    .spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                cloud1.addCustomEffect(new PotionEffect(
                    PotionEffectType.WITHER,
                    500,
                    1,
                    false,
                    false
                ), true);
                cloud1.addCustomEffect(new PotionEffect(
                    PotionEffectType.CONFUSION,
                    500,
                    2,
                    false,
                    false
                ), true);
                cloud1.setDuration(200);
                cloud1.setDurationOnUse(0);
                cloud1.setRadiusOnUse(0);
                cloud1.setColor(Color.GRAY);
                cloud1.setRadius(4);
                break;
            case "PYRO_POWDER":
                snowball.getWorld().createExplosion(loc, 4F);
                break;
            case "THIOACETONE":
                snowball.getWorld().createExplosion(loc, 1F, false, false);
                AreaEffectCloud cloud2 = (AreaEffectCloud) snowball.getWorld()
                    .spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                cloud2.addCustomEffect(new PotionEffect(
                    PotionEffectType.CONFUSION,
                    1200,
                    9,
                    false,
                    false
                ), true);
                cloud2.setDuration(600);
                cloud2.setDurationOnUse(0);
                cloud2.setRadiusOnUse(0);
                cloud2.setColor(Color.ORANGE);
                cloud2.setRadius(10);
                break;
        }
    }
}
