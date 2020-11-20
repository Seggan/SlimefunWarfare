package io.github.seggan.slimefunwarfare.georesources;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

public class Arsenic implements GEOResource {
    private final NamespacedKey key;
    private final ItemStack item;

    public Arsenic(ItemStack stack) {
        key = new NamespacedKey(SlimefunWarfare.getInstance(), "arsenic");
        item = stack;
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {
        if (environment == Environment.THE_END) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxDeviation() {
        return 1;
    }

    @Override
    public String getName() {
        return "Arsenic";
    }

    @Override
    public ItemStack getItem() {
        return item.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
