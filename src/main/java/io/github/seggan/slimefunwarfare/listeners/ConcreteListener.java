package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;
import java.util.Random;
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
    public void onConcreteExplodeEntity(EntityExplodeEvent e) {
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
