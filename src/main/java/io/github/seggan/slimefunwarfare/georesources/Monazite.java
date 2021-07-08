package io.github.seggan.slimefunwarfare.georesources;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Monazite implements GEOResource {
    private final NamespacedKey key;
    private final ItemStack item;

    public Monazite(ItemStack stack) {
        key = SlimefunWarfare.inst().getKey("mozanite");
        item = stack;
    }

    @Override
    public int getDefaultSupply(@Nonnull World.Environment environment, @Nonnull Biome biome) {
        if (biome == Biome.BASALT_DELTAS) {
            return 1;
        }

        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return 2;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Monazite";
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return item;
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
