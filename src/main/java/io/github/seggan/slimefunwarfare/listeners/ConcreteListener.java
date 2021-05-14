package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ConcreteListener implements Listener {

    private static final List<Location> EXPLOSIONS = new ArrayList<>();

    @EventHandler
    public void onConcreteExplode(BlockExplodeEvent e) {
        Location location = e.getBlock().getLocation();
        if (EXPLOSIONS.contains(location)) {
            EXPLOSIONS.remove(location);
        } else {
            Random random = ThreadLocalRandom.current();
            int percentChance = SlimefunWarfare.inst().getConfig().getInt("explosions.concrete-explode-chance", 1, 100);
            Iterator<Block> iter = e.blockList().iterator();

            while (iter.hasNext()) {
                if (BlockStorage.check(iter.next(), "REINFORCED_CONCRETE")) {
                    if (random.nextInt(100) > percentChance) {
                        iter.remove();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplodeConcrete(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        if (entity.hasMetadata("isNuke")) {
            e.setCancelled(true);
            EXPLOSIONS.add(e.getLocation());
            entity.getWorld().createExplosion(e.getLocation(), entity.getMetadata("rad").get(0).asFloat(),
                true);
        } else {
            Random random = ThreadLocalRandom.current();
            int percentChance = SlimefunWarfare.inst().getConfig().getInt("explosions.concrete-explode-chance", 1, 100);
            Iterator<Block> iter = e.blockList().iterator();

            while (iter.hasNext()) {
                if (BlockStorage.check(iter.next(), "REINFORCED_CONCRETE")) {
                    if (random.nextInt(100) > percentChance) {
                        iter.remove();
                    }
                }
            }
        }
    }
}
