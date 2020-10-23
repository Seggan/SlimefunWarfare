package io.github.seggan.slimefunwarfare;

import org.bukkit.entity.LlamaSpit;
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
        if (bullet.hasMetadata("damage")) {
            e.setDamage(bullet.getMetadata("damage").get(0).asInt());
            if (bullet.hasMetadata("isFire") && bullet.getMetadata("isFire").get(0).asBoolean()) {
                e.getEntity().setFireTicks(e.getEntity().getFireTicks() + 60);
            }
        }
    }
}
