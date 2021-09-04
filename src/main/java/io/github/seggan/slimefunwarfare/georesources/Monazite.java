package io.github.seggan.slimefunwarfare.georesources;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Monazite implements GEOResource {
    private final NamespacedKey key;
    private final ItemStack item;

    public Monazite(ItemStack stack) {
        key = AbstractAddon.createKey("mozanite");
        item = stack;
    }

    @Override
    public int getDefaultSupply(@Nonnull World.Environment environment, @Nonnull Biome biome) {
        if (biome == Biome.BASALT_DELTAS) {
            return Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion") ? 1 : 4;
        }

        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion") ? 2 : 3;
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
