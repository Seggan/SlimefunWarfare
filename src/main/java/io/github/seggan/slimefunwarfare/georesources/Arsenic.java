package io.github.seggan.slimefunwarfare.georesources;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Arsenic implements GEOResource {
    private final NamespacedKey key;
    private final ItemStack item;

    public Arsenic(ItemStack stack) {
        key = new NamespacedKey(SlimefunWarfare.inst(), "arsenic");
        item = stack;
    }

    @Override
    public int getDefaultSupply(@Nonnull Environment environment, @Nonnull Biome biome) {
        if (environment == Environment.THE_END) {
            return 5;
        } else {
            return 1;
        }
    }

    @Override
    public int getMaxDeviation() {
        return 1;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Arsenic";
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return item.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
