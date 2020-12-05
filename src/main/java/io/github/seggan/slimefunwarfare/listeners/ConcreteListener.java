package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.lists.Constants;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ConcreteListener implements Listener {

    @EventHandler
    public void onConcreteExplode(BlockExplodeEvent e) {
        Random random = ThreadLocalRandom.current();
        int percentChance = SlimefunWarfare.getConfigSettings().getConcreteExplodePercent();
        Iterator<Block> iter = e.blockList().iterator();

        while (iter.hasNext()) {
            if (BlockStorage.check(iter.next(), "REINFORCED_CONCRETE")) {
                if (random.nextInt(100) > percentChance) {
                    iter.remove();
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplodeConcrete(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        if (entity.hasMetadata("isBetterExplosive")) {
            e.blockList().clear();
            float radius = entity.getMetadata("rad").get(0).asFloat();
            List<Block> blocks = Util.getBlocksAroundCenter(e.getLocation(), Math.round(radius));
            Set<Material> explodableMaterials = Util.deserializeMaterialSet(
                entity.getMetadata("explodableBlocks").get(0).asString());
            Iterator<Block> iter = blocks.iterator();

            while (iter.hasNext()) {
                Block b = iter.next();
                Material type = b.getType();
                if (!(Constants.TNT_WEAK.contains(type) || explodableMaterials.contains(type))) {
                    blocks.remove(b);
                }
            }

            e.blockList().addAll(blocks);
        } else {
            Random random = ThreadLocalRandom.current();
            int percentChance = SlimefunWarfare.getConfigSettings().getConcreteExplodePercent();
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
    public void onBetterExplosivePrime(ExplosionPrimeEvent e) {
        Entity entity = e.getEntity();
        if (entity.hasMetadata("isBetterExplosive")) {
            e.setRadius(entity.getMetadata("rad").get(0).asFloat());
        }
    }
}
