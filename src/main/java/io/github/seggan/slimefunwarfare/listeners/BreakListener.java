package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Items;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.ThreadLocalRandom;

public class BreakListener implements Listener {

    private final double boraxChance = SlimefunWarfare.inst().getConfig().getDouble("guns.borax-drop-chance", 0, 100);

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onStoneBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.STONE || Items.BORAX.getItem().isDisabled()) return;
        if (ThreadLocalRandom.current().nextDouble(100) < boraxChance) {
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.BORAX.clone());
        }
    }
}
